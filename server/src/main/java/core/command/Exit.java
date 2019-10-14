package core.command;

import command.Command;
import communication.Info;
import core.ClientConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Exit extends CommandHandler {

    public Exit(Command command, ClientConnection clientConnection) {
        super(command, clientConnection);
    }

    @Override
    public void execute(Path currentWorkingDirectory) throws IOException
    {
        fClientConnection.disableIncomingCommnication();
    }
}