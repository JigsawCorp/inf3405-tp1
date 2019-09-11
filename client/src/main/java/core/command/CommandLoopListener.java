package core.command;

import java.util.Scanner;

public class CommandLoopListener {
    private static boolean loopAtive = false;
    public static void startListening()
    {
        loopAtive = true;
        while (loopAtive) {
            CommandLoopListener.listenToCommand();
        }
    }

    public static void stopListening()
    {
        loopAtive = false;
    }

    private static void listenToCommand()
    {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        
    }
}
