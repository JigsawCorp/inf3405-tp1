package core.command.handlers;

import command.Command;
import core.command.CommandLoopListener;

import java.io.IOException;

public class Exit extends CommandHandler{

    @Override
    public void execute(Command command) throws IOException
    {
        sendCommand(command);
        CommandLoopListener.stopListening();
        System.out.println("Vous avez été déconnecté avec succès");
    }
}
