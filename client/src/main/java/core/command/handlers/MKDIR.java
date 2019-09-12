package core.command.handlers;

import command.Command;
import communication.Message;

import java.io.IOException;

public class MKDIR extends CommandHandler {

    @Override
    public void execute(Command command) throws IOException
    {
        sendCommand(command);
        handleResponse();
    }
}
