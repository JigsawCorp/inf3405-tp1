package core.command;

import command.Command;
import communication.Info;
import core.ClientConnection;
import core.CommunicationHandler;
import utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class MKDIR extends CommandHandler {

    public MKDIR(Command command, ClientConnection clientConnection) {
        super(command, clientConnection);
    }

    @Override
    public void execute(Path currentWorkingDirectory) throws IOException
    {
        if (FileUtils.checkPathExists(fCommand.fArguments[0], fClientConnection.fCurrentWorkingDirectory)) {
            sendMessage(new Info("Un fichier ou dossier avec ce nom existe déjà.", Info.InfoType.RESPONSE, Info.Status.FAILURE));
        } else {
            new File(FileUtils.getCombinedPath(fCommand.fArguments[0], fClientConnection.fCurrentWorkingDirectory).toString()).mkdir();
            sendMessage(new Info("Le dossier " + fCommand.fArguments[0] + " a été créé.", Info.InfoType.RESPONSE, Info.Status.SUCCESS));
        }
    }

}
