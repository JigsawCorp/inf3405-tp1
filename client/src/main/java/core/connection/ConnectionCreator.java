package core.connection;

import connection.ConnectionValidator;

import java.io.IOException;
import java.util.Scanner;

public class ConnectionCreator {

    public static ConnectionHandler createConnectionHandler()
    {
        boolean continuePrompting = true;

        while (continuePrompting) {
            String IPAddress = promptIPAddress();
            while (!ConnectionValidator.validateIp(IPAddress)) {
                IPAddress = promptIPAddress();
            }

            int port = promptPort();
            while (!ConnectionValidator.validatePort(port)) {
                port = promptPort();
            }

            try {
                ConnectionHandler.getInstance().connectToServer(new ServerInformation(IPAddress, port));
                continuePrompting = false;
            } catch (IOException e) {
                System.out.println("Erreur lors de la connexion au serveur! Erreur complète:");
                System.out.println(e.toString());
            }
        }

        return ConnectionHandler.getInstance();

    }

    private static String promptIPAddress()
    {
        System.out.println("Please enter the server IP address:");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();

    }

    private static int promptPort()
    {
        int port = 0;
        boolean continuePrompting = true;

        do {
            System.out.println("Please enter the server port:");
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
