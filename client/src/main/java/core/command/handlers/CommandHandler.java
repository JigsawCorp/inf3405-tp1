package core.command.handlers;

import command.Command;
import communication.Message;
import core.connection.ConnectionHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public abstract class CommandHandler {
    private ConnectionHandler fConnectionHandler;

    protected Command fCommand;

    CommandHandler(Command command)
    {
        fConnectionHandler = ConnectionHandler.getInstance();
        fCommand = command;
    }

    public abstract void execute() throws Exception;

    public static CommandHandler instantiate(Command command)
    {
        switch (command.fCommandName) {
            case CD:
                return new CD(command);
            case LS:
                return new LS(command);
            case MKDIR:
                return new MKDIR(command);
            case UPLOAD:
                return new Upload(command);
            case DOWNLOAD:
                return new Download(command);
            case EXIT:
                return new Exit(command);
            default:
                return null;
        }
    }

    protected void sendCommand(Command command) throws IOException
    {
        fConnectionHandler.sendMessage(command);
    }

    protected Message waitForMessage() throws Exception
    {
        return fConnectionHandler.waitForMessage();
    }

    protected void handleResponse() throws Exception
    {
        Message response = fConnectionHandler.waitForMessage();

        if (response != null && response.dType == Message.Type.INFO) {
            System.out.println(response.toString());
        }
    }

    protected void downloadFile(String filePath) throws IOException
    {
        fConnectionHandler.downloadFile(filePath);
    }

    protected void uploadFile(String localFilePath) throws IOException
    {
        fConnectionHandler.sendFile(localFilePath);
    }

    protected boolean fileExists(String filePath) {
        File f = new File(filePath);

        return f.exists();
    }
}
