package core.command;

import command.Command;
import communication.Info;
import core.ClientConnection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * CommandHandler for the ls command.
 */
public class LS extends CommandHandler {

    /**
     * Constructor.
     * @param command The command to execute.
     * @param clientConnection The client connection.
     */
    public LS(Command command, ClientConnection clientConnection) {
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
        String result = "";
        Path base = currentWorkingDirectory;
        File f = new File(currentWorkingDirectory.toString());
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory()) {
                result += "[Folder] " + base.relativize(files[i].toPath()).toString() + System.lineSeparator();
            } else {
                result += "[File] " + base.relativize(files[i].toPath()).toString() + System.lineSeparator();
            }
        }
        sendMessage(new Info(result, Info.InfoType.RESPONSE, Info.Status.SUCCESS));
    }
}
