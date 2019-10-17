package core.command;

import command.Command;
import communication.Info;
import core.ClientConnection;
import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

public class Download extends CommandHandler {

    public Download(Command command, ClientConnection clientConnection) {
        super(command, clientConnection);
    }

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
