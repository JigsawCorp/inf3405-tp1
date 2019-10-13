package core;

import connection.ConnectionValidator;

import java.util.Scanner;

public class PortInputHandler {
    public static int promptPort()
    {
        System.out.println("Please enter the listening port (between 5000 and 5050)");
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                int port = Integer.parseInt(input);

                if (ConnectionValidator.validatePort(port))
                    return port;
            } catch (NumberFormatException ignored) {}

            System.out.println("Invalid entry. Please try again:");
        } while (true);
    }
}
