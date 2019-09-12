package application;

import core.command.CommandLoopListener;
import core.connection.ConnectionCreator;
import core.connection.ConnectionHandler;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Application {
    public static void main(String[] args) throws IOException
    {
        ConnectionHandler connectionHandler = ConnectionCreator.createConnectionHandler();
        CommandLoopListener.startListening();
        connectionHandler.closeConnection();
    }
}
