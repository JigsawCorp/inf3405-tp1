package core.command.handlers;

import command.Command;

/**
 * CommandHandler for the ls command.
 */
public class LS extends CommandHandler {

    /**
     * Constructor.
     * @param command The command to execute.
     */
    public LS(Command command)
    {
        super(command);
    }

    /**
     * Execute the command.
     * @throws Exception If executing the command fails.
     */
    @Override
    public void execute() throws Exception
    {
        // Send the command to the server and handle its response.
        sendCommand(fCommand);
        handleResponse();
    }

}
