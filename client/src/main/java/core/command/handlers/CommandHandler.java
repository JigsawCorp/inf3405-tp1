package core.command.handlers;

import command.Command;
import communication.Message;
import core.connection.ConnectionHandler;

import java.io.IOException;

public abstract class CommandHandler {
    private ConnectionHandler fConnectionHandler;

    CommandHandler()
    {
        fConnectionHandler = ConnectionHandler.getInstance();
    }

    public abstract void execute(Command command) throws Exception;

    void sendCommand(Command command) throws IOException
    {
        fConnectionHandler.sendMessage(command);
    }

    Message waitForMessage() throws IOException
    {
        return fConnectionHandler.waitForMessage();
    }

    void handleResponse() throws IOException
    {
        Message response = fConnectionHandler.waitForMessage();

        if (response != null && response.dType == Message.Type.INFO) {
            System.out.println(response.toString());
        }
    }

    void downloadFile(String filePath) throws IOException
    {
        fConnectionHandler.downloadFile(filePath);
    }

    void uploadFile(String localFilePath) throws IOException
    {
        fConnectionHandler.sendFile(localFilePath);
    }
}
