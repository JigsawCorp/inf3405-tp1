package core;

import command.Command;
import communication.Message;
import core.command.CommandHandler;

import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ClientConnection implements Runnable {
    private Socket fSocket = null;
    private Thread fThread;
    public CommunicationHandler fCommunicationHandler;
    private boolean fAcceptClientCommunication;
    public Path fCurrentWorkingDirectory;

    public ClientConnection(Socket socket) {
        fSocket = socket;
        fThread = new Thread(this);
        fCommunicationHandler = new CommunicationHandler(socket);
        fCurrentWorkingDirectory = FileSystems.getDefault().getPath(".").toAbsolutePath();
        fThread.start();
    }

    @Override
    public void run() {
        try {
            enableIncomingCommunication();
        } catch (Exception e) {

        }
    }

    public void enableIncomingCommunication()
    {
        if (!fAcceptClientCommunication) {
            fAcceptClientCommunication = true;
            while (fAcceptClientCommunication) {
                Message message = fCommunicationHandler.receiveMessage();
                CommandLogger.logCommand((Command) message, fSocket);
                CommandHandler command = CommandHandler.instantiate(((Command) message), this);
                try {
                    command.execute(fCurrentWorkingDirectory);
                } catch (Exception e) {

                }
            }
        }
    }

    public void disableIncomingCommnication()
    {
        fAcceptClientCommunication = false;
    }
}
