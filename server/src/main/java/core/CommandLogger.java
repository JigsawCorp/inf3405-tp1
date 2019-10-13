package core;

import command.Command;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandLogger {
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
