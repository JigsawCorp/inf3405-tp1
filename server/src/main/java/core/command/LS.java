package core.command;

import command.Command;

import java.io.IOException;

public class LS extends CommandHandler {

    @Override
    public void execute(Command command) throws IOException
    {
        sendCommand(command);
        handleResponse();
    }

}
