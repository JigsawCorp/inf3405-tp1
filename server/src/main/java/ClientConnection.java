import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable {
    private Socket fSocket = null;
    private Thread fThread;
    private CommunicationHandler fCommunicationHandler;
    public ClientConnection(Socket socket) {
        fSocket = socket;
        fThread = new Thread(this);
        fThread.start();
        fCommunicationHandler = new CommunicationHandler();
    }

    /*
    public void start() throws IOException, ClassNotFoundException {
        // Création d'un input stream. Ce stream contiendra les données envoyées par le
        // client.
        System.out.println("Waiting for info!");
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(fSocket.getInputStream()));
        System.out.println("Received info 1!");
        System.out.println(in.readObject());
        // La fonction readObject est bloquante! Ainsi, le serveur arrête son exécution
        // et attend la réception de l'objet envoyé par le client!
        //List<String> strings = (List<String>) in.readObject()
        System.out.println("Received info3!");
    }
    */

    @Override
    public void run() {
        try {
            System.out.println("Starting thread!");
        } catch (Exception e) {

        }
    }
}
