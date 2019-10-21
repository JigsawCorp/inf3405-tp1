package core.command;

import command.Command;
import core.ClientConnection;

import java.nio.file.Path;

/**
 * CommandHandler for the exit command.
 */
public class Exit extends CommandHandler {

    /**
     * Constructor.
     * @param command The command to execute.
     * @param clientConnection The client connection.
     */
    public Exit(Command command, ClientConnection clientConnection) {
        super(command, clientConnection);
    }

    /**
     * Execute the command.
     * @param currentWorkingDirectory The CWD to execute the command in.
     */
    @Override
    public void execute(Path currentWorkingDirectory)
    {
        fClientConnection.disableIncomingCommunication();
    }
}