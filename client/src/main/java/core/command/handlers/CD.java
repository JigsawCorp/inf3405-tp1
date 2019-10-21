package core.command.handlers;

import command.Command;

/**
 * CommandHandler for the cd command.
 */
public class CD extends CommandHandler {

    /**
     * Constructor.
     * @param command The command to execute.
     */
    public CD(Command command) {
        super(command);
    }

    /**
     * Execute the command.
     * @throws Exception If executing the command fails.
     */
    @Override
    public void execute() throws Exception
    {
        // Send the command to the server and handle the response.
        sendCommand(fCommand);
        handleResponse();
    }
}
