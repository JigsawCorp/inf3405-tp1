package core.command.handlers;

import command.Command;
import communication.Message;
import core.connection.ConnectionHandler;

import java.io.File;
import java.io.IOException;

/**
 * Abstract class for all possible commands. Useful to create proper commands and define standard
 * methods to communicate with ConnectionHandler.
 */
public abstract class CommandHandler {
    // Hold the actual command to execute. Contains arguments and command type.
    protected Command fCommand;
    // ConnectionHandler to communicate with server.
    private ConnectionHandler fConnectionHandler;

    /**
     * Initializes the CommandHandler.
     * @param command The command to execute.
     */
    protected CommandHandler(Command command)
    {
        fConnectionHandler = ConnectionHandler.getInstance();
        fCommand = command;
    }

    /**
     * Execute the command.
     * @throws Exception If executing the command fails.
     */
    public abstract void execute() throws Exception;

    /**
     * Factory method to create the right CommandHandler child class.
     * @param command The command to execute.
     * @return A CommandHandler child class.
     */
    public static CommandHandler instantiate(Command command)
    {
        // Create the right child class from the command name.
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

    /**
     * Send the command to the server.
     * @param command The command to execute.
     * @throws IOException If there is an issue communicating with the server.
     */
    protected void sendCommand(Command command) throws IOException
    {
        fConnectionHandler.sendMessage(command);
    }

    /**
     * Waits for a message from the server.
     * @return A message sent from the server.
     * @throws Exception If there is an issue communicating with the server.
     */
    protected Message waitForMessage() throws Exception
    {
        return fConnectionHandler.waitForMessage();
    }

    /**
     * Displays a response from the server to the user.
     * @throws Exception If there is an issue communicating with the server.
     */
    protected void handleResponse() throws Exception
    {
        Message response = fConnectionHandler.waitForMessage();

        // If there is a response and said message is Info.
        if (response != null && response.dType == Message.Type.INFO) {
            System.out.println(response.toString());
        }
    }

    /**
     * Downloads a file from the server.
     * @param filePath The filePath to the file we want to download.
     * @throws IOException If there is an issue communicating with the server.
     */
    protected void downloadFile(String filePath) throws IOException
    {
        fConnectionHandler.downloadFile(filePath);
    }

    /**
     * Uploads a file to the server.
     * @param localFilePath The local file we want to upload to the remote server.
     * @throws IOException If there is an issue communicating with the server.
     */
    protected void uploadFile(String localFilePath) throws IOException
    {
        fConnectionHandler.sendFile(localFilePath);
    }

    /**
     * Returns wether a file exists or not on the local storage.
     * @param filePath The file path we want to verify.
     * @return True if the file exists on the local storage.
     */
    protected boolean fileExists(String filePath) {
        File f = new File(filePath);

        return f.exists();
    }
}
