package core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Manages all client connections.
 */
public class ClientConnectionManager {
    // The server socket.
    private ServerSocket fServerSocket;
    // List of all connected clients.
    private ArrayList<ClientConnection> clients;
    // True to accept incoming connections.
    private boolean acceptClientConnections;

    /**
     * Constructor.
     * @param serverSocket The server socket.
     */
    public ClientConnectionManager(ServerSocket serverSocket)
    {
        fServerSocket = serverSocket;
        clients = new ArrayList<>();
        acceptClientConnections = false;
    }

    /**
     * Adds a new client socket.
     * @param clientSocket The client socket to add.
     */
    public void addClient(Socket clientSocket)
    {
        // Try to create a new ClientConnection and add it to the list.
        try {
            ClientConnection newConnection = new ClientConnection(clientSocket);
            clients.add(newConnection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Enables incoming client connections.
     */
    public void enableIncomingConnections()
    {
        if (!acceptClientConnections) {
            acceptClientConnections = true;
            // Wait to receive a new connection and try to add a new client.
            while (acceptClientConnections) {
                try {
                    addClient(fServerSocket.accept());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
