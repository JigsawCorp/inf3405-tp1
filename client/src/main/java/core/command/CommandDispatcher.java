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

        CommandHandler commandToExecute = null;

        switch (command.fCommandName) {
            case CD:
                commandToExecute = new CD();
                break;
            case LS:
                commandToExecute = new LS();
                break;
            case MKDIR:
                commandToExecute = new MKDIR();
                break;
            case UPLOAD:
                commandToExecute = new Upload();
                break;
            case DOWNLOAD:
                commandToExecute = new Download();
                break;
            case EXIT:
                commandToExecute = new Exit();
                break;
            default:
                commandToExecute = null;
                break;
        }

        if (commandToExecute != null) {
            try {
                commandToExecute.execute(command);
            } catch (Exception e) {
                System.out.println("Erreur lors de l'exécution de la commande. Erreur complète:");
                System.out.println(e.toString());
            }
        }

    }

    private static Command parseCommand(String command) throws Exception {
        String[] splitCommand = command.split("\\s+");
        Command.CommandName commandName = null;
        try {
            commandName = Command.CommandName.valueOf(splitCommand[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Erreur lors de l'interprétation de la commande! " + splitCommand[0] + " n'est pas une commande recconnue.");
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
