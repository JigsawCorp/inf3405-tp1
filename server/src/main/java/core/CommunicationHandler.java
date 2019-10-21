package core;

import communication.Message;

import java.io.*;
import java.net.Socket;
import java.nio.file.FileAlreadyExistsException;

/**
 * Handles all communication with clients.
 */
public class CommunicationHandler {
    // The socket to the client.
    private Socket fSocket;

    /**
     * Constuctor.
     * @param socket The client socket.
     */
    public CommunicationHandler(Socket socket)
    {
        fSocket = socket;
    }

    /**
     * Wait for a client message and return it.
     * @return The message sent from the client.
     */
    public Message receiveMessage()
    {
        ObjectInputStream inStream = null;
        // Try receiving the message.
        try {
            inStream = new ObjectInputStream(fSocket.getInputStream());
            return (Message) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors de la réception d'une réponse du serveur! Erreur complète:");
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * Sends a message to the client.
     * @param message The message to send.
     * @throws IOException If sending the message failed.
     */
    public void sendMessage(Message message) throws IOException
    {
        ObjectOutputStream outStream = null;

        // Try sending the message.
        try {
            outStream = new ObjectOutputStream(fSocket.getOutputStream());
            outStream.writeObject(message);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'envoi d'une message au serveur! Erreur complète:");
            System.out.println(e.toString());
        } finally {
            if (outStream != null) outStream.flush();
        }
    }

    /**
     * Sends a file to the client.
     * @param filePath Path to the local file to send to the client.
     * @throws IOException If sending the file failed.
     */
    public void sendFile(String filePath) throws IOException
    {
        InputStream inStream = null;
        DataOutputStream outStream = null;

        try {
            File file = new File(filePath);
            // Buffer to read the local file.
            byte[] buffer = new byte[8192];
            inStream = new FileInputStream(file);
            outStream = new DataOutputStream(new BufferedOutputStream(fSocket.getOutputStream()));

            // Send file length first.
            outStream.writeLong(file.length());

            int count;
            // Read the file into the buffer and write the buffer to the client.
            while ((count = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, count);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de l'envoi du fichier! Erreur complète:");
            System.out.println(e.toString());
        } finally {
            if (outStream != null) outStream.flush();
            if (inStream != null) inStream.close();
        }
    }

    /**
     * Receive a file from the client.
     * @param filePath The file path to receive.
     * @throws IOException If receiving a file failed.
     */
    public void receiveFile(String filePath) throws IOException
    {
        DataInputStream inStream = null;
        FileOutputStream outStream = null;

        try {
            inStream = new DataInputStream(new BufferedInputStream(fSocket.getInputStream()));


            // If the file already exists locally, we cannot receive it.
            File f = new File(filePath);
            if (f.exists()) {
                throw new FileAlreadyExistsException(filePath + " already exists!");
            }

            outStream = new FileOutputStream(filePath);

            // Read file length first.
            long fileLength = inStream.readLong();

            // Read each byte of the file and write them to the new file.
            for(int i = 0; i < fileLength; ++i) outStream.write(inStream.read());

        } catch (Exception e) {
            System.out.println("Erreur lors de la réception du fichier! Erreur complète:");
            System.out.println(e.toString());
        } finally {
            if (outStream != null) outStream.close();
        }
    }




}
