package core.command.handlers;

import command.Command;
import communication.Info;

import java.io.IOException;

public class Download extends CommandHandler {

    public Download(Command command) {
        super(command);
    }

    @Override
    public void execute() throws Exception
    {
        if (!fileExists(fCommand.fArguments[0])){
            // Send the command, await for server confirmation, then download file
            sendCommand(fCommand);
            Info message = (Info) waitForMessage();
            if (message.fInfoType == Info.InfoType.ACK && message.fStatus == Info.Status.SUCCESS) {
                downloadFile(fCommand.fArguments[0]);
                System.out.println("Le fichier " + fCommand.fArguments[0] + " a bien été téléchargé.");
            }
            // If the download didn't work, print the server error
            else {
                System.out.println(message.fMessage);
            }
        }
        else {
            System.out.println(fCommand.fArguments[0] + " existe déjà!");
        }
    }
}
