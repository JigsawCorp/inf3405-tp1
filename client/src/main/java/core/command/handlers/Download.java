package core.command.handlers;

import command.Command;

import java.io.IOException;

public class Download extends CommandHandler {

    @Override
    public void execute(Command command) throws IOException
    {
        sendCommand(command);
        downloadFile(command.fArguments[0]);
        handleResponse();
    }
}
