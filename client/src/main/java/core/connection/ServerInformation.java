package core.connection;

/**
 * Holds connection information for the server.
 */
public class ServerInformation {
    // IP Address of server.
    public String fIPAddress;
    // Port of server.
    public int fPort;

    /**
     * Constructor.
     * @param IPAddress IP address of the server.
     * @param port Port of the server.
     */
    public ServerInformation(String IPAddress, int port)
    {
        fIPAddress = IPAddress;
        fPort = port;
    }
}
