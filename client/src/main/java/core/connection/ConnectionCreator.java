package core.connection;

import connection.ConnectionValidator;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class ConnectionCreator {

    public static ConnectionHandler createConnectionHandler()
    {
        boolean continuePrompting = true;

        do {
            String IPAddress = "";
            int port = 0;
            while (continuePrompting) {
                IPAddress = promptIPAddress();
                try {
                    continuePrompting = !ConnectionValidator.validateIp(IPAddress);
                } catch (InvalidParameterException e) {
                    System.out.println(e.getMessage());
                }
            }

            continuePrompting = true;

            while (continuePrompting) {
                port = promptPort();
                continuePrompting = !ConnectionValidator.validatePort(port);
                if (continuePrompting) {
                    System.out.println("Le port entré n'est pas entre 5000 et 5050!");
                }
            }

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

    private static String promptIPAddress()
    {
        System.out.println("Veuillez entrer l'adresse IP du serveur:");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    private static int promptPort()
    {
        int port = 0;
        boolean continuePrompting;

        do {
            System.out.println("Veuillez entrer le port du serveur:");
            Scanner scanner = new Scanner(System.in);

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
