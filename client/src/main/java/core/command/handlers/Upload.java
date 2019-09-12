package core.command.handlers;

import command.Command;

import java.io.IOException;

public class Upload extends CommandHandler {

    @Override
    public void execute(Command command) throws IOException
    {
        sendCommand(command);
        uploadFile(command.fArguments[0]);
        handleResponse();
    }
}
