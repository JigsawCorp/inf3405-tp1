package core.command;

import command.Command;
import communication.Info;
import core.ClientConnection;
import core.CommunicationHandler;
import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CD extends CommandHandler {

    public CD(Command command, ClientConnection clientConnection) {
        super(command, clientConnection);
    }

    @Override
    public void execute(Path currentWorkingDirectory) throws IOException
    {
        // If user requested an invalid directory
        if (!FileUtils.checkPathExists(fCommand.fArguments[0], currentWorkingDirectory)) {
            sendMessage(new Info("Ce dossier n'existe pas.", Info.InfoType.RESPONSE, Info.Status.FAILURE));
        }
        // If user requested a valid directory
        else {
            Path path = FileUtils.getCombinedPath(fCommand.fArguments[0], currentWorkingDirectory);
            fClientConnection.fCurrentWorkingDirectory = path;
            sendMessage(new Info("Vous êtes dans le dossier " + path.getFileName(), Info.InfoType.RESPONSE, Info.Status.SUCCESS));
        }
    }

}
