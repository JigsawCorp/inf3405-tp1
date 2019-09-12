package core.command.handlers;

import command.Command;
import core.command.CommandLoopListener;

public class Exit extends CommandHandler{

    @Override
    public void execute(Command command)
    {
        CommandLoopListener.stopListening();
    }
}
