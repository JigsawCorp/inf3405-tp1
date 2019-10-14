package core;

import communication.Message;

import java.io.*;
import java.net.Socket;
import java.nio.file.FileAlreadyExistsException;

public class CommunicationHandler {
    private boolean fAcceptClientCommunication;
    private Socket fSocket;
    public CommunicationHandler(Socket socket)
    {
        fAcceptClientCommunication = false;
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
        } finally {
            //if (inStream != null) inStream.close();
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

    public void sendFile(String filePath)
    {

    }

    public void receiveFile(String filePath) throws IOException
    {
        InputStream inStream = null;
        FileOutputStream outStream = null;

        try {
            inStream = fSocket.getInputStream();

            File f = new File(filePath);
            if (f.exists()) {
                throw new FileAlreadyExistsException(filePath + " already exists!");
            }

            outStream = new FileOutputStream(filePath);

            byte[] bytes = new byte[8192];

            int count;
            while ((count = inStream.read(bytes)) > 0) {
                outStream.write(bytes, 0, count);
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la réception du fichier! Erreur complète:");
            System.out.println(e.toString());
        } finally {
            if (outStream != null) outStream.close();
            //if (inStream != null) inStream.close();
        }
    }




}
