package core;

import communication.Message;

import java.io.*;
import java.net.Socket;
import java.nio.file.FileAlreadyExistsException;

public class CommunicationHandler {
    private Socket fSocket;
    public CommunicationHandler(Socket socket)
    {
        fSocket = socket;
    }

    public Message receiveMessage()
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

    public void sendMessage(Message message) throws IOException {
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

    public void sendFile(String filePath) throws IOException
    {
        InputStream inStream = null;
        DataOutputStream outStream = null;

        try {
            File file = new File(filePath);
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

    public void receiveFile(String filePath) throws IOException
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
