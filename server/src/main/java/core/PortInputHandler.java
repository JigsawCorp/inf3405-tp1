package core;

import connection.ConnectionValidator;

import java.util.Scanner;

/**
 * Utility class to prompt the user for a port.
 */
public class PortInputHandler {
    /**
     * Prompts the user for a server port.
     * @return The server port.
     */
    public static int promptPort()
    {
        // Prompt the user for a port until a valid one is inputted.
        System.out.println("Veuillez entrer le port d'écoute du serveur (entre 5000 et 5050):");
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                int port = Integer.parseInt(input);

                if (ConnectionValidator.validatePort(port))
                    return port;
            } catch (NumberFormatException ignored) {}

            System.out.println("Entrée invalide. Veuillez réessayer:");
        } while (true);
    }
}
