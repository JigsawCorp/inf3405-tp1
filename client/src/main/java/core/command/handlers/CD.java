package core.command.handlers;

import command.Command;
import communication.Message;
import core.connection.ConnectionHandler;

import java.io.IOException;

public class CD extends CommandHandler {

    @Override
    public void execute(Command command) throws IOException
    {
        sendCommand(command);
        handleResponse();
    }
}
