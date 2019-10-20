package core.command;

import command.Command;
import core.command.handlers.*;

import java.util.Arrays;

public class CommandDispatcher {
    public static void dispatchCommand(String rawCommand)
    {
        Command command = null;
        try {
            command = parseCommand(rawCommand);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }

        CommandHandler commandToExecute = CommandHandler.instantiate(command);

        if (commandToExecute != null) {
            try {
                commandToExecute.execute();
            } catch (Exception e) {
                System.out.println("Erreur lors de l'exécution de la commande. Erreur complète:");
                System.out.println(e.getMessage());
            }
        }

    }

    private static Command parseCommand(String command) throws Exception {
        String[] splitCommand = command.split("\\s+");
        Command.CommandName commandName = null;
        try {
            commandName = Command.CommandName.valueOf(splitCommand[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Erreur lors de l'interprétation de la commande! " + splitCommand[0] + " n'est pas une commande reconnue.");
        }

        return new Command(commandName, parseArguments(splitCommand));

    }

    private static String[] parseArguments(String[] command) {
        if (command.length < 2) {
            return new String[]{};
        }

        return Arrays.copyOfRange(command, 1, command.length);

    }
}
