import core.ClientConnectionManager;
import core.PortInputHandler;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Entry-point class for the server application.
 */
public class Server {
    /**
     * Start the server application.
     * @param args
     */
    public static void main(String[] args)
    {
        // Prompt the user for a port and start the server with said port.
        try {
            int port = PortInputHandler.promptPort();
            run(port);
        } catch (IOException e) {
            System.out.println("Le serveur a rencontré une erreur.\n" +
                    "Détails: " + e + "\n" +
                    "Arrêt du serveur...");
        } catch (Exception e) {
            System.out.println("Erreur interne rencontrée. Arrêt du serveur...");
        }
    }

    /**
     * Starts the server on the given port.
     * @param port Port to map the server to.
     * @throws IOException If running the server failed.
     */
    private static void run(int port) throws IOException
    {
        // Listen for incoming client connections.
        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Le serveur est en cours d'exécution");
            ClientConnectionManager clientConnectionManager = new ClientConnectionManager(serverSocket);
            clientConnectionManager.enableIncomingConnections();
        }
    }
}
