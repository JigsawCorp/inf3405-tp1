package core.command.handlers;

import command.Command;
import communication.Info;

import java.io.IOException;

public class Upload extends CommandHandler {

    public Upload(Command command) {
        super(command);
    }

    @Override
    public void execute() throws Exception
    {
        if (fileExists(fCommand.fArguments[0])) {
            // Send the command, wait for server confirmation, then upload the file
            sendCommand(fCommand);
            Info message = (Info) waitForMessage();
            if (message.fInfoType == Info.InfoType.ACK && message.fStatus == Info.Status.SUCCESS) {
                uploadFile(fCommand.fArguments[0]);
                handleResponse();
            }
            // If the upload didn't work, print the server error
            else {
                System.out.println(message.fMessage);
            }
        }
        else {
            System.out.println("Le fichier n'existe pas dans votre répertoire local.");
        }

    }
}
