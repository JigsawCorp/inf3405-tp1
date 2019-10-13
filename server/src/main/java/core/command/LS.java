package core.command;

import command.Command;
import communication.Info;
import core.ClientConnection;
import core.CommunicationHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LS extends CommandHandler {

    public LS(Command command, ClientConnection clientConnection) {
        super(command, clientConnection);
    }

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
        //System.out.println(base.relativize(Paths.get(Arrays.toString(f.listFiles())));
        sendMessage(new Info(result, Info.InfoType.RESPONSE, Info.Status.SUCCESS));
    }

}
