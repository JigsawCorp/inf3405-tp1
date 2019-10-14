package core.command.handlers;

import command.Command;
import communication.Info;

import java.io.IOException;

public class Download extends CommandHandler {

    @Override
    public void execute(Command command) throws IOException
    {
        sendCommand(command);
        Info message = (Info) waitForMessage();
        if (message.fInfoType == Info.InfoType.ACK && message.fStatus == Info.Status.SUCCESS) {
            downloadFile(command.fArguments[0]);
            System.out.println("Le fichier" + command.fArguments[0] + " a bien été téléchargé.");
        }
    }
}
