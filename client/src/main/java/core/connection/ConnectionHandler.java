package core.connection;

import communication.Message;

import java.io.*;
import java.net.Socket;
import java.nio.file.FileAlreadyExistsException;

/**
 * Handles any communication with a server.
 */
public class ConnectionHandler {
    // The remote server information.
    private ServerInformation fServerInfo;
    // The socket to the server.
    private Socket fSocket = null;
    // Current instance. Used to enforce Singleton pattern.
    private static ConnectionHandler sInstance = null;

    /**
     * Returns the ConnectionHandler instance. If none exists, create a new one.
     * @return The ConnectionHandler.
     */
    public static ConnectionHandler getInstance()
    {
        // If ConnectionHandler has not been instantiated yet, create it.
        if (sInstance == null) {
            sInstance = new ConnectionHandler();
        }

        return ConnectionHandler.sInstance;
    }

    /**
     * Connect to a remote server.
     * @param serverInfo The server information that describes the remote server.
     * @throws IOException  If the connection failed.
     */
    void connectToServer(ServerInformation serverInfo) throws IOException
    {
        fServerInfo = serverInfo;
        // If we already have a socket instance, close it.
        if (fSocket != null && fSocket.isConnected()) {
            closeConnection();
        }

        fSocket = new Socket(fServerInfo.fIPAddress, fServerInfo.fPort);
    }

    /**
     * Closes the connection to the remote server.
     * @throws IOException If closing the connection failed.
     */
    public void closeConnection() throws IOException
    {
        fSocket.close();
    }

    /**
     * Sends a message to the remote server.
     * @param message The message to send.
     * @throws IOException If sending the message failed.
     */
    public void sendMessage(Message message) throws IOException
    {
        ObjectOutputStream outStream = null;

        // Try to send a message.
        try {
            outStream = new ObjectOutputStream(fSocket.getOutputStream());
            outStream.writeObject(message);
        } finally {
            if (outStream != null) outStream.flush();
        }
    }

    /**
     * Waits for a message from the server.
     * @return The message received from the server.
     * @throws Exception If receiving a message failed.
     */
    public Message waitForMessage() throws Exception
    {
        ObjectInputStream inStream;
        // Receive a message and return it.
        try {
            inStream = new ObjectInputStream(fSocket.getInputStream());
            return (Message) inStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Mauvais type reçu du serveur!", e);
        }
    }

    /**
     * Sends a local file to the remote server.
     * @param fileName The local fileName.
     * @throws IOException If sending a file failed.
     */
    public void sendFile(String fileName) throws IOException
    {
        InputStream inStream = null;
        DataOutputStream outStream = null;

        try {
            File file = new File(fileName);
            // Buffer to hold part of the file.
            byte[] buffer = new byte[8192];
            inStream = new FileInputStream(file);
            outStream = new DataOutputStream(new BufferedOutputStream(fSocket.getOutputStream()));

            // Send file length first.
            outStream.writeLong(file.length());

            // Write all bytes to the server. Use the buffer to read the file.
            int count;
            while ((count = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, count);
            }
        } catch (FileNotFoundException e) {
            throw new IOException("Le fichier n'existe pas dans votre répertoire local.", e);
        } finally {
            if (outStream != null) outStream.flush();
            if (inStream != null) inStream.close();
        }
    }

    /**
     * Downloads a remote file from the server to the local storage.
     * @param filePath The file path on the remote server.
     * @throws IOException If downloading the file failed.
     */
    public void downloadFile(String filePath) throws IOException
    {
        DataInputStream inStream = null;
        FileOutputStream outStream = null;

        try {
            inStream = new DataInputStream(new BufferedInputStream(fSocket.getInputStream()));

            File f = new File(filePath);
            // If the file already exists on the local storage, we cannot download it.
            if (f.exists()) {
                throw new FileAlreadyExistsException(filePath + " existe déjà!");
            }

            outStream = new FileOutputStream(filePath);

            // Read file length first.
            long fileLength = inStream.readLong();

            // Write all bytes received to the new file.
            for(int i = 0; i < fileLength; ++i) outStream.write(inStream.read());
        } finally {
            if (outStream != null) outStream.close();
        }
    }
}
