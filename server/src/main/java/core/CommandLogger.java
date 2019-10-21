package core;

import command.Command;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logs client messages to the console.
 */
public class CommandLogger {

    /**
     * Logs a given command from a client socket to the console.
     * @param command The command to log.
     * @param clientSocket The client who sent the message.
     */
    public static void logCommand(Command command, Socket clientSocket)
    {
        String log = "";
        // Add IP and port
        log += "[" + clientSocket.getRemoteSocketAddress().toString().substring(1);
        // Add date
        log += " - " + formatDate() + "]";
        // Add command
        log += " : " + command.toString();

        System.out.println(log);
    }

    /**
     * Format the current date.
     * @return The formatted date.
     */
    private static String formatDate()
    {
        String formattedDate = "";
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
        formattedDate += dateFormatter.format(date) + "@" + timeFormatter.format(date);

        return formattedDate;
    }
}
