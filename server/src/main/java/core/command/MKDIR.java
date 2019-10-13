package core.command;

import command.Command;
import core.ClientConnection;
import core.CommunicationHandler;

import java.io.IOException;
import java.nio.file.Path;

public class MKDIR extends CommandHandler {

    public MKDIR(Command command, ClientConnection clientConnection) {
        super(command, clientConnection);
    }

    @Override
    public void execute(Path currentWorkingDirectory) throws IOException
    {

        sendMessage(fCommand);
        handleResponse();
    }

}
