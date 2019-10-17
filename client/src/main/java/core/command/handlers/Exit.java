package core.command.handlers;

import command.Command;
import core.command.CommandLoopListener;

import java.io.IOException;

public class Exit extends CommandHandler{

    public Exit(Command command) {
        super(command);
    }

    @Override
    public void execute() throws IOException
    {
        sendCommand(fCommand);
        CommandLoopListener.stopListening();
        System.out.println("Vous avez été déconnecté avec succès");
    }
}
