package core.command;

import command.Command;
import communication.Info;
import core.ClientConnection;
import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

/**
 * CommandHandler for the cd command.
 */
public class CD extends CommandHandler {

    /**
     * Constructor.
     * @param command The command to execute.
     * @param clientConnection The client connection.
     */
    public CD(Command command, ClientConnection clientConnection) {
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
        // If user requested an invalid directory.
        if (!FileUtils.checkPathExists(fCommand.fArguments[0], currentWorkingDirectory)) {
            sendMessage(new Info("Ce dossier n'existe pas.", Info.InfoType.RESPONSE, Info.Status.FAILURE));
        }
        // If user requested a valid directory.
        else {
            Path path = FileUtils.getCombinedPath(fCommand.fArguments[0], currentWorkingDirectory);
            fClientConnection.fCurrentWorkingDirectory = path;

            String currentFolder;

            if (path.getFileName() == null) {
                currentFolder = path.toString();
            } else {
                currentFolder = path.getFileName().toString();
            }

            sendMessage(new Info("Vous êtes dans le dossier " + currentFolder, Info.InfoType.RESPONSE, Info.Status.SUCCESS));
        }
    }

}
