package core.command;

import command.Command;
import communication.Message;
import core.CommunicationHandler;

import java.io.IOException;

public abstract class CommandHandler {
    private CommunicationHandler fCommunicationHandler;

    public CommandHandler(CommunicationHandler communicationHandler)
    {
        fCommunicationHandler = communicationHandler;
    }

    public abstract void execute(Command command) throws Exception;

    void sendCommand(Command command) throws IOException
    {
        fCommunicationHandler.sendMessage(command);
    }

    void handleResponse() throws IOException
    {
        Message response = fCommunicationHandler.receiveMessage();

        if (response != null && response.dType == Message.Type.INFO) {
            System.out.println(response.toString());
        }
    }

    void receiveFile(String filePath) throws IOException
    {
        fCommunicationHandler.receiveFile(filePath);
    }

    void sendFile(String localFilePath) throws IOException
    {
        fCommunicationHandler.sendFile(localFilePath);
    }
}