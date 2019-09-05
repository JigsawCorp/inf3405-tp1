package core.connection;

import connection.ConnectionValidator;

import java.util.Scanner;

public class ConnectionCreator {

    public static ConnectionHandler createConnectionHandler()
    {
        String IPAdress = promptIPAddress();
        while (!ConnectionValidator.validateIp(IPAdress)) {
            IPAdress = promptIPAddress();
        }

        int port = promptPort();
        while(!ConnectionValidator.validatePort(port)) {
            port = promptPort();
        }

        return new ConnectionHandler(new ServerInformation(IPAdress, port));

    }

    private static String promptIPAddress()
    {
        System.out.println("Please enter the server IP adress:");
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
