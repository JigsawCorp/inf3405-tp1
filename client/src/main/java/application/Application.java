package application;

import core.connection.ConnectionCreator;
import core.connection.ConnectionHandler;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Application {
    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException
    {
        Path path = FileSystems.getDefault().getPath(".").toAbsolutePath();
        System.out.println(path.toString());
        ConnectionHandler connectionHandler = ConnectionCreator.createConnectionHandler();
        while(true){}
    }
}
