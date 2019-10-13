package core.command;

import command.Command;
import communication.Message;
import core.CommunicationHandler;

import java.io.IOException;

public abstract class CommandHandler {
    private CommunicationHandler fCommunicationHandler;
    protected Command fCommand;

    protected CommandHandler(Command command, CommunicationHandler communicationHandler)
    {
        fCommunicationHandler = communicationHandler;
    }

    public static CommandHandler instantiate(Command command, CommunicationHandler communicationHandler)
    {
        switch (command.fCommandName) {
            case CD:
                return new CD(command, communicationHandler);
            case LS:
                return new LS(command, communicationHandler);
            default:
                return null;
        }
    }

    public abstract void execute(String currentWorkingDirectory) throws Exception;

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