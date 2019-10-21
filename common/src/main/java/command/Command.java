package command;

import communication.Message;

import java.io.Serializable;

/**
 * Type of Message used to hold command arguments and name. Useful to pass the command between the client and server.
 */
public class Command extends Message implements Serializable {
    // Types of commands.
    public enum CommandName { CD, LS, MKDIR, UPLOAD, DOWNLOAD, EXIT}

    // Name of the command.
    public CommandName fCommandName;
    // Arguments of the command.
    public String[] fArguments;

    /**
     * Constructor.
     * @param commandName The name of the command.
     * @param arguments The arguments of the command.
     */
    public Command(CommandName commandName, String[] arguments)
    {
        super(Type.COMMAND);
        fCommandName = commandName;
        fArguments = arguments;
    }

    /**
     * Get a String representation of the command.
     * @return A string representation of the command.
     */
    @Override
    public String toString() {
        String res = "";
        res += fCommandName.toString().toLowerCase();
        for (int i = 0; i < fArguments.length; ++i) {
            res += " " + fArguments[i];
        }
        return res;
    }
}
