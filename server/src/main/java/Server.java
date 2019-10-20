import core.ClientConnectionManager;
import core.PortInputHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args)
    {
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

    private static void run(int port) throws IOException {
        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Le serveur est en cours d'exécution");
            ClientConnectionManager clientConnectionManager = new ClientConnectionManager(serverSocket);
            clientConnectionManager.enableIncomingConnections();
        }
    }
}
