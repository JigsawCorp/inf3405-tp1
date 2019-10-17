package core.command;

import java.util.Scanner;

public class CommandLoopListener {
    private static boolean loopActive = false;
    public static void startListening()
    {
        loopActive = true;
        while (loopActive) {
            CommandLoopListener.listenToCommand();
        }
    }

    public static void stopListening()
    {
        loopActive = false;
    }

    private static void listenToCommand()
    {
        System.out.println("Veuillez entrer une commande:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        CommandDispatcher.dispatchCommand(userInput);
        
    }
}
