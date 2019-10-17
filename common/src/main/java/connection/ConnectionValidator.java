package connection;

public class ConnectionValidator {
    private static final int minPort = 5000;
    private static final int maxPort = 5050;
    private static final int maxIpByteVal = 255;
    private static final int minIpByteVal = 0;

    public static boolean validateIp(String IP) throws IllegalArgumentException
    {
        String[] ipBytes = IP.split("\\.");

        // Return false if less than 4 bytes
        if (ipBytes.length != 4) {
            throw new IllegalArgumentException("L'adresse IP n'a pas 4 bytes!");
        }

        for (int i = 0; i < ipBytes.length; ++i) {
            int byteVal;

            try {
                byteVal = Integer.parseInt(ipBytes[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("L'adresse IP contient des caractères non-numérique!");
            }

            if (byteVal < minIpByteVal || byteVal > maxIpByteVal) {
                throw new IllegalArgumentException("L'adresse IP contient des bytes de longueur invalide!");
            }
        }

        return true;
    }

    public static boolean validatePort(int port)
    {
        return port >= minPort && port <= maxPort;
    }

}
