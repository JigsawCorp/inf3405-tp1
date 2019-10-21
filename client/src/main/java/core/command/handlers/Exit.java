package core.command.handlers;

import command.Command;
import core.command.CommandLoopListener;

import java.io.IOException;

/**
 * CommandHandler for the exit command.
 */
public class Exit extends CommandHandler{

    /**
     * Constructor.
     * @param command The command to execute.
     */
    public Exit(Command command) {
        super(command);
    }

    /**
     * Execute the command.
     * @throws IOException If executing the command fails.
     */
    @Override
    public void execute() throws IOException
    {
        // Tell the server the client is exiting.
        sendCommand(fCommand);
        // Stop the command loop.
        CommandLoopListener.stopListening();
        System.out.println("Vous avez été déconnecté avec succès");
    }
}
