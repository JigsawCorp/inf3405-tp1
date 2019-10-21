package application;

import core.command.CommandLoopListener;
import core.connection.ConnectionCreator;
import core.connection.ConnectionHandler;

import java.io.IOException;

/**
 * Entry-point class for the client application
 */
public class Client {
    /**
     * Starts the client application
     * @param args
     * @throws IOException If closing the connection doesn't work
     */
    public static void main(String[] args) throws IOException
    {
        // Connect to server
        ConnectionHandler connectionHandler = ConnectionCreator.createConnectionHandler();
        // Start listening to user commands
        CommandLoopListener.startListening();
        // Close the connection
        connectionHandler.closeConnection();
    }
}
