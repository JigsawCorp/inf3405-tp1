import core.ClientConnectionManager;
import core.PortInputHandler;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args)
    {
        try {
            int port = PortInputHandler.promptPort();
            run(port);
        } catch (IOException e) {
            System.out.println("Error encountered during data input/output.\n" +
                    "Details: " + e + "\n" +
                    "Closing server...");
        } catch (Exception e) {
            System.out.println("Internal error encountered. The server will now stop.");
        }
    }

    private static void run(int port) throws IOException {
        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is running");
            ClientConnectionManager clientConnectionManager = new ClientConnectionManager(serverSocket);
            clientConnectionManager.enableIncomingConnections();
        }
    }
}
