package core.command;

import command.Command;
import core.command.handlers.*;

import java.util.Arrays;

/**
 * Dispatches commands from raw inputs.
 */
public class CommandDispatcher {

    /**
     * From a raw string, parse the command and execute it.
     * @param rawCommand The raw command. Will start with the command name followed by optional arguments.
     */
    public static void dispatchCommand(String rawCommand)
    {
        Command command = null;
        // Try parsing the command. If we cannot parse it, tell the user why.
        try {
            command = parseCommand(rawCommand);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // Get a CommandHandler for the given command.
        CommandHandler commandToExecute = CommandHandler.instantiate(command);

        // If the command is valid, execute the command and display any errors to the user.
        if (commandToExecute != null) {
            try {
                commandToExecute.execute();
            } catch (Exception e) {
                System.out.println("Erreur lors de l'exécution de la commande. Erreur complète:");
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Parses a raw command and return a formatted command.
     * @param command The raw command.
     * @return A formatted command.
     * @throws Exception If the command cannot be parsed.
     */
    private static Command parseCommand(String command) throws Exception
    {
        // Make a String array for the command name and arguments.
        String[] splitCommand = command.split("\\s+");
        Command.CommandName commandName = null;

        // Get the CommandName from the raw String.
        try {
            commandName = Command.CommandName.valueOf(splitCommand[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Erreur lors de l'interprétation de la commande! " + splitCommand[0] + " n'est pas une commande reconnue.");
        }

        // Separate the arguments from the command name/
        String[] arguments = Arrays.copyOfRange(splitCommand, 1, splitCommand.length);

        // If the arguments are valid for current command, then return the newly formatted command.
        if (ArgumentValidator.validateArguments(commandName, arguments)) {
            return new Command(commandName, arguments);
        }

        return null;
    }
}
