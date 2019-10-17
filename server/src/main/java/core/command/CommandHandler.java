package core.command;

import command.Command;
import communication.Message;
import core.ClientConnection;
import core.CommunicationHandler;

import java.io.IOException;
import java.nio.file.Path;

public abstract class CommandHandler {
    protected ClientConnection fClientConnection;
    protected Command fCommand;

    protected CommandHandler(Command command, ClientConnection clientConnection)
    {
        fClientConnection = clientConnection;
        fCommand = command;
    }

    public static CommandHandler instantiate(Command command, ClientConnection clientConnection)
    {
        switch (command.fCommandName) {
            case CD:
                return new CD(command, clientConnection);
            case LS:
                return new LS(command, clientConnection);
            case MKDIR:
                return new MKDIR(command, clientConnection);
            case UPLOAD:
                return new Upload(command, clientConnection);
            case DOWNLOAD:
                return new Download(command, clientConnection);
            case EXIT:
                return new Exit(command, clientConnection);
            default:
                return null;
        }
    }

    public abstract void execute(Path currentWorkingDirectory) throws IOException;

    void sendMessage(Message message) throws IOException
    {
        fClientConnection.fCommunicationHandler.sendMessage(message);
    }

    void receiveFile(String filePath) throws IOException
    {
        fClientConnection.fCommunicationHandler.receiveFile(filePath);
    }

    void sendFile(String localFilePath) throws IOException
    {
        fClientConnection.fCommunicationHandler.sendFile(localFilePath);
    }
}