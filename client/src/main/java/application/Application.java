package application;

import core.connection.ConnectionCreator;
import core.connection.ConnectionHandler;

public class Application {
    public static void main(String[] args)
    {
        ConnectionHandler connectionHandler = ConnectionCreator.createConnectionHandler();
        while(true){}
    }
}
