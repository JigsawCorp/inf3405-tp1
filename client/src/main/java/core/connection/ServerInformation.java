package core.connection;

public class ServerInformation {
    public String fIPAdress;
    public int fPort;

    public ServerInformation(String IPAddress, int port)
    {
        fIPAdress = IPAddress;
        fPort = port;
    }
}
