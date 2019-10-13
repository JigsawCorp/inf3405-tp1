import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Stack;

/** Le client doit etre capable de lire un fichier texte et d'envoyer son contenu au serveur qui retransmettra aussitot
 *  son contenu au client. Ce dernier devra intercepter le contenu du fichier texte. Une fois la reception terminee, le
 *  serveur devra inverser le contenu du fichier de sorte a ce que la premiere ligne recue soit la derniere ligne
 *  envoyee vers le client. **/
//L'implémentation du serveur n'est pas multi-threaded. Ainsi, la connection de
//plusieurs clients en même temps au serveur ne fonctionnera pas! A vous de threader le serveur
//pour qu'il puisse avoir la capacité d'accepter plusieurs clients.
public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        try {
            int port = PortInputHandler.promptPort();
            run(port);
        } catch (IOException e) {
            System.out.println("Error encountered during data input/output.\n" +
                    "Details: " + e + "\n" +
                    "Closing server...");
        } catch (Exception e) {
            System.out.println("Internal error encountered. The server will now stop.");
        }
    }

    private static void run(int port) throws IOException, ClassNotFoundException {
        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is running");
            ClientConnectionManager clientConnectionManager = new ClientConnectionManager(serverSocket);
            clientConnectionManager.enableIncomingConnections();
            /*
            try {
                // Création du socket du serveur en utilisant le port 5000.
                serverSocket = new ServerSocket(port);
                System.out.println("Server is running");
                // Ici, la fonction accept est bloquante! Ainsi, l'exécution du serveur s'arrête
                // ici et attend la connection d'un client avant de poursuivre.
                while(true) {
                    socket = serverSocket.accept();
                    // TODO: Handle each connection separately so individual connection failures don't impact the whole server.
                    ClientConnection clientConnection = new ClientConnection(socket);
                    clientConnection.start();
                }
            } finally {
                serverSocket.close();
                socket.close();
            } */
        }
    }
}
