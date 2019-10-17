package core.command;

import command.Command;
import core.ClientConnection;

import java.nio.file.Path;

public class Exit extends CommandHandler {

    public Exit(Command command, ClientConnection clientConnection) {
        super(command, clientConnection);
    }

    @Override
    public void execute(Path currentWorkingDirectory)
    {
        fClientConnection.disableIncomingCommunication();
    }
}