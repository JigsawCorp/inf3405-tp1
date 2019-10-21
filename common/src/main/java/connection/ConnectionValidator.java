package connection;

/**
 * Validates Connection information format.
 */
public class ConnectionValidator {
    // Port threshold.
    private static final int minPort = 5000;
    private static final int maxPort = 5050;
    // IP byte threshold.
    private static final int maxIpByteVal = 255;
    private static final int minIpByteVal = 0;

    /**
     * Validates an IP address format.
     * @param IP The IP address to validate.
     * @return True if the IP is in a valid format.
     * @throws IllegalArgumentException If the IP is not in a valid format.
     */
    public static boolean validateIp(String IP) throws IllegalArgumentException
    {
        // Split all bytes in an array.
        String[] ipBytes = IP.split("\\.");

        // Return false if less than 4 bytes
        if (ipBytes.length != 4) {
            throw new IllegalArgumentException("L'adresse IP n'a pas 4 bytes!");
        }

        // Validate the values of each byte.
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

    /**
     * Validates the port format.
     * @param port The port to validate.
     * @return True if the port is in a valid format. False otherwise.
     */
    public static boolean validatePort(int port)
    {
        return port >= minPort && port <= maxPort;
    }

}
