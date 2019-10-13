package command;

import communication.Message;

import java.io.Serializable;

public class Command extends Message implements Serializable {
    public enum CommandName { CD, LS, MKDIR, UPLOAD, DOWNLOAD, EXIT}

    public CommandName fCommandName;
    public String[] fArguments;

    public Command(CommandName commandName, String[] arguments)
    {
        super(Type.COMMAND);
        fCommandName = commandName;
        fArguments = arguments;
    }

    @Override
    public String toString() {
        String res = "";
        res += "Command name = " + fCommandName.toString() + ", arguments = " + fArguments.toString();
        return res;
    }
}
