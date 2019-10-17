package core.command.handlers;

import command.Command;
import communication.Message;

import java.io.IOException;

public class MKDIR extends CommandHandler {

    public MKDIR(Command command) {
        super(command);
    }

    @Override
    public void execute() throws Exception
    {
        sendCommand(fCommand);
        handleResponse();
    }
}
