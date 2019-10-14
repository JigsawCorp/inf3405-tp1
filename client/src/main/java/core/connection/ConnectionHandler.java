package core.connection;


import communication.Message;

import java.io.*;
import java.net.Socket;
import java.nio.file.FileAlreadyExistsException;

public class ConnectionHandler {
    private ServerInformation fServerInfo;
    private Socket fSocket = null;
    private static ConnectionHandler sInstance = null;

    public static ConnectionHandler getInstance()
    {
        if (sInstance == null) {
            sInstance = new ConnectionHandler();
        }

        return ConnectionHandler.sInstance;

    }

    void connectToServer(ServerInformation serverInfo) throws IOException
    {
        fServerInfo = serverInfo;
        if (fSocket != null && fSocket.isConnected()) {
            closeConnection();
        }
        fSocket = new Socket(fServerInfo.fIPAdress, fServerInfo.fPort);
    }

    public void closeConnection() throws IOException
    {
        fSocket.close();
    }

    public void sendMessage(Message message) throws IOException
    {
        ObjectOutputStream outStream = null;

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

    public Message waitForMessage() throws IOException
    {
        ObjectInputStream inStream = null;
        try {
            inStream = new ObjectInputStream(fSocket.getInputStream());
            return (Message) inStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors de la réception d'une réponse du serveur! Erreur complète:");
            System.out.println(e.toString());
            return null;
        }
    }

    public void sendFile(String fileName) throws IOException
    {
        InputStream inStream = null;
        DataOutputStream outStream = null;

        try {
            File file = new File(fileName);
            byte[] buffer = new byte[8192];
            inStream = new FileInputStream(file);
            outStream = new DataOutputStream(new BufferedOutputStream(fSocket.getOutputStream()));

            // Send file length
            outStream.writeLong(file.length());

            int count;
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

    public void downloadFile(String filePath) throws IOException
    {
        DataInputStream inStream = null;
        FileOutputStream outStream = null;

        try {
            inStream = new DataInputStream(new BufferedInputStream(fSocket.getInputStream()));

            File f = new File(filePath);
            if (f.exists()) {
                throw new FileAlreadyExistsException(filePath + " already exists!");
            }

            outStream = new FileOutputStream(filePath);

            // Read file length
            long fileLength = inStream.readLong();

            for(int i = 0; i < fileLength; ++i) outStream.write(inStream.read());

        } catch (Exception e) {
            System.out.println("Erreur lors de la réception du fichier! Erreur complète:");
            System.out.println(e.toString());
        } finally {
            if (outStream != null) outStream.close();
        }
    }



}
