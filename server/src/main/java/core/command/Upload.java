package core.command;

import command.Command;
import communication.Info;
import core.ClientConnection;
import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

/**
 * CommandHandler for the upload command.
 */
public class Upload extends CommandHandler {

    /**
     * Constructor.
     * @param command The command to execute.
     * @param clientConnection The client connection.
     */
    public Upload(Command command, ClientConnection clientConnection) {
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
        if (FileUtils.checkPathExists(fCommand.fArguments[0], fClientConnection.fCurrentWorkingDirectory)) {
            sendMessage(new Info("Le fichier existe déjà.", Info.InfoType.RESPONSE, Info.Status.FAILURE));
        } else {
            sendMessage(new Info("", Info.InfoType.ACK, Info.Status.SUCCESS));
            receiveFile(FileUtils.getCombinedPath(fCommand.fArguments[0], fClientConnection.fCurrentWorkingDirectory).toString());
            sendMessage(new Info("Le fichier " + fCommand.fArguments[0] + " a bien été téléversé." , Info.InfoType.RESPONSE, Info.Status.SUCCESS));
        }
    }

}
