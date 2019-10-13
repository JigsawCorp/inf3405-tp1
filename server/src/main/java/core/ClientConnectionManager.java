package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnectionManager {
    private ServerSocket fServerSocket;
    private ArrayList<ClientConnection> clients;
    private boolean acceptClientConnections;

    public ClientConnectionManager(ServerSocket serverSocket)
    {
        fServerSocket = serverSocket;
        clients = new ArrayList<>();
        acceptClientConnections = false;
    }

    public void addClient(Socket clientSocket)
    {
        System.out.println("Adding client");

        try {
            ClientConnection newConnection = new ClientConnection(clientSocket);
            clients.add(newConnection);
        } catch (Exception e) {

        }
    }

    public void enableIncomingConnections()
    {
        if (!acceptClientConnections) {
            acceptClientConnections = true;
            while (acceptClientConnections) {
                try {
                addClient(fServerSocket.accept());
                } catch (IOException e) {

                }
            }
        }
    }

    public void disableIncomingConnections()
    {
        acceptClientConnections = false;
    }
}
