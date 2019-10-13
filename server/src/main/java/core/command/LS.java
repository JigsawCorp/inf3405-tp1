package core.command;

import command.Command;
import core.CommunicationHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class LS extends CommandHandler {

    public LS(Command command, CommunicationHandler communicationHandler) {
        super(command, communicationHandler);
    }

    @Override
    public void execute(String currentWorkingDirectory) throws IOException
    {
        Path base = Paths.get(currentWorkingDirectory);
        File f = new File(currentWorkingDirectory);
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; ++i) {
            if (files[i].isDirectory()) {
                System.out.println("[Folder] " + base.relativize(files[i].toPath()).toString());
            } else {
                System.out.println("[File] " + base.relativize(files[i].toPath()).toString());
            }
        }
        //System.out.println(base.relativize(Paths.get(Arrays.toString(f.listFiles())));
        sendCommand(fCommand);
        handleResponse();
    }

}
