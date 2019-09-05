package connection;

public class ConnectionValidator {
    private static final int minPort = 5000;
    private static final int maxPort = 5050;
    private static final int maxIpByteVal = 255;
    private static final int minIpByteVal = 0;

    public static boolean validateIp(String IP)
    {
        String[] ipBytes = IP.split(".");

        // Return false if less than 4 bytes
        if (ipBytes.length != 4) {
            return false;
        }

        for (int i = 0; i < ipBytes.length; ++i) {
            int byteVal;

            try {
                byteVal = Integer.parseInt(ipBytes[i]);
            } catch (NumberFormatException e) {
                return false;
            }

            if (byteVal < minIpByteVal || byteVal > maxIpByteVal) {
                return false;
            }
        }

        return true;
    }

    public static boolean validatePort(int port)
    {
        return port >= minPort && port <= maxPort;
    }

}
