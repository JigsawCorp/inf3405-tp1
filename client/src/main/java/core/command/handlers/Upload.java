package core.command.handlers;

import command.Command;
import communication.Info;
import communication.Message;

import java.io.IOException;

public class Upload extends CommandHandler {

    @Override
    public void execute(Command command) throws IOException
    {
        sendCommand(command);
        Info message = (Info) waitForMessage();
        if (message.fInfoType == Info.InfoType.ACK && message.fStatus == Info.Status.SUCCESS) {
            uploadFile(command.fArguments[0]);
            handleResponse();
        }
    }
}
