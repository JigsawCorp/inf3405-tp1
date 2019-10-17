package core.command.handlers;

import command.Command;

import java.io.IOException;

public class CD extends CommandHandler {

    public CD(Command command) {
        super(command);
    }

    @Override
    public void execute() throws Exception
    {
        sendCommand(fCommand);
        handleResponse();
    }
}
