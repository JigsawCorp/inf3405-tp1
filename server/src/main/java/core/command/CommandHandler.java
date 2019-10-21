package core.command;

import command.Command;
import communication.Message;
import core.ClientConnection;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Abstract class for all possible commands. Useful to create proper commands and define standard
 * methods to communicate with CommunicationHandler.
 */
public abstract class CommandHandler {
    // The client connection.
    protected ClientConnection fClientConnection;
    // The command to execute.
    protected Command fCommand;

    /**
     * Constructor.
     * @param command The command to execute.
     * @param clientConnection The client connection.
     */
    protected CommandHandler(Command command, ClientConnection clientConnection)
    {
        fClientConnection = clientConnection;
        fCommand = command;
    }

    /**
     * Factory method to create the proper child CommandHandler.
     * @param command The command to execute.
     * @param clientConnection The client connection.
     * @return A CommandHandler child class.
     */
    public static CommandHandler instantiate(Command command, ClientConnection clientConnection)
    {
        // Create the right child class.
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

    /**
     * Executes the command.
     * @param currentWorkingDirectory The CWD to execute the command in.
     * @throws IOException If the execution failed.
     */
    public abstract void execute(Path currentWorkingDirectory) throws IOException;

    /**
     * Sends a message to the client.
     * @param message The message to send to the client.
     * @throws IOException If sending the message failed.
     */
    void sendMessage(Message message) throws IOException
    {
        fClientConnection.fCommunicationHandler.sendMessage(message);
    }

    /**
     * Receives a file from the client.
     * @param filePath The file path to receive from the client.
     * @throws IOException If receiving a file failed.
     */
    void receiveFile(String filePath) throws IOException
    {
        fClientConnection.fCommunicationHandler.receiveFile(filePath);
    }

    /**
     * Sends a file to the client.
     * @param localFilePath The local file to send.
     * @throws IOException If sending the file failed.
     */
    void sendFile(String localFilePath) throws IOException
    {
        fClientConnection.fCommunicationHandler.sendFile(localFilePath);
    }
}