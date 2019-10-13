package core.command;

import command.Command;
import communication.Info;
import core.CommunicationHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LS extends CommandHandler {

    public LS(Command command, CommunicationHandler communicationHandler) {
        super(command, communicationHandler);
    }

    @Override
    public void execute(String currentWorkingDirectory) throws IOException
    {
        String result = "";
        Path base = Paths.get(currentWorkingDirectory);
        File f = new File(currentWorkingDirectory);
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
