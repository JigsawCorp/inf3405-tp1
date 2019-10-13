package core.command;

import command.Command;
import core.CommunicationHandler;

import java.io.IOException;

public class CD extends CommandHandler {

    public CD(Command command, CommunicationHandler communicationHandler) {
        super(command, communicationHandler);
    }

    @Override
    public void execute(String currentWorkingDirectory) throws IOException
    {
        sendMessage(fCommand);
        handleResponse();
    }

}
