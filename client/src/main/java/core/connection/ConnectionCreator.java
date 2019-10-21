package core.connection;

import connection.ConnectionValidator;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * Utility class to create a connection to a server.
 */
public class ConnectionCreator {

    /**
     * Create a ConnectionHandler.
     * @return A newly created ConnectionHandler.
     */
    public static ConnectionHandler createConnectionHandler()
    {
        boolean continuePrompting = true;

        do {
            String IPAddress = "";
            int port = 0;

            // Continue prompting the user for an IP address until he inputs a valid one.
            while (continuePrompting) {
                IPAddress = promptIPAddress();
                try {
                    // Validate the IP address format.
                    continuePrompting = !ConnectionValidator.validateIp(IPAddress);
                } catch (InvalidParameterException e) {
                    System.out.println(e.getMessage());
                }
            }

            continuePrompting = true;

            // Continue prompting the user for a port until he inputs a valid one.
            while (continuePrompting) {
                port = promptPort();
                // Validate the server port format.
                continuePrompting = !ConnectionValidator.validatePort(port);
                if (continuePrompting) {
                    System.out.println("Le port entré n'est pas entre 5000 et 5050!");
                }
            }

            // Attempt to connect to the server. If the connection fails, ask the user for new IP and port.
            try {
                ConnectionHandler.getInstance().connectToServer(new ServerInformation(IPAddress, port));
                continuePrompting = false;
            } catch (IOException e) {
                System.out.println("Erreur lors de la connexion au serveur! Erreur complète:");
                System.out.println(e.getMessage());
                continuePrompting = true;
            }
        } while (continuePrompting);

        return ConnectionHandler.getInstance();
    }

    /**
     * Prompts the user for the server IP address.
     * @return The server IP address to connect to.
     */
    private static String promptIPAddress()
    {
        System.out.println("Veuillez entrer l'adresse IP du serveur:");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    /**
     * Prompts the user for a server port.
     * @return The server port to connect to.
     */
    private static int promptPort()
    {
        int port = 0;
        boolean continuePrompting;

        // Continue prompting the user until he inputs a valid port.
        do {
            System.out.println("Veuillez entrer le port du serveur:");
            Scanner scanner = new Scanner(System.in);

            // If we catch an exception, port is invalid and continue prompting.
            try {
                continuePrompting = false;
                port = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                continuePrompting = true;
            }
        } while(continuePrompting);

        return port;
    }
}
