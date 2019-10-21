package core.command;

import java.util.Scanner;

/**
 * Loop listening for user inputs.
 */
public class CommandLoopListener {
    private static boolean loopActive = false;

    /**
     * Starts listening to user input. When given an input, try to execute a command.
     */
    public static void startListening()
    {
        loopActive = true;
        while (loopActive) {
            // Prompt the user and listen to one command.
            CommandLoopListener.listenToCommand();
        }
    }

    /**
     * Stops listening to user input.
     */
    public static void stopListening()
    {
        loopActive = false;
    }

    /**
     * Prompts the user to input a command and tries to dispatch it.
     */
    private static void listenToCommand()
    {
        // Prompt user and listen to input.
        System.out.println("Veuillez entrer une commande:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // Dispatch the command.
        CommandDispatcher.dispatchCommand(userInput);
    }
}
