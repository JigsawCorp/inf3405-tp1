package core.command;

import command.Command;
import communication.Info;
import core.ClientConnection;
import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

/**
 * CommandHandler for the download command.
 */
public class Download extends CommandHandler {

    /**
     * Constructor.
     * @param command The command to execute.
     * @param clientConnection The client connection.
     */
    public Download(Command command, ClientConnection clientConnection) {
        super(command, clientConnection);
    }

    /**
     * Execute the command.
     * @param currentWorkingDirectory The CWD to execute the command in.
     * @throws IOException If executing the command fails.
     */
    @Override
    public void execute(Path currentWorkingDirectory) throws IOException
    {
        // If user requested a file that doesn't exist
        if (!FileUtils.fileExists(fCommand.fArguments[0], fClientConnection.fCurrentWorkingDirectory)) {
            sendMessage(new Info("Le fichier n'existe pas.", Info.InfoType.RESPONSE, Info.Status.FAILURE));
        }
        // File is valid
        else {
            sendMessage(new Info("", Info.InfoType.ACK, Info.Status.SUCCESS));
            sendFile(FileUtils.getCombinedPath(fCommand.fArguments[0], fClientConnection.fCurrentWorkingDirectory).toString());
        }
    }

}
