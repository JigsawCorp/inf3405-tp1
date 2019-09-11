package command;

import java.io.Serializable;

public class Command implements Serializable {
    public String fCommandName;
    public String[] fArguments;

    public Command(String commandName, String[] arguments)
    {
        fCommandName = commandName;
        fArguments = arguments;
    }

}
