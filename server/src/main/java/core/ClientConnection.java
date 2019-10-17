package core;

import command.Command;
import communication.Message;
import core.command.CommandHandler;

import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class ClientConnection implements Runnable {
    public CommunicationHandler fCommunicationHandler;
    public Path fCurrentWorkingDirectory;

    private Socket fSocket;
    private Thread fThread;
    private boolean fAcceptClientCommunication;

    public ClientConnection(Socket socket) {
        fSocket = socket;
        fThread = new Thread(this);
        fCommunicationHandler = new CommunicationHandler(socket);
        fCurrentWorkingDirectory = FileSystems.getDefault().getPath(".").toAbsolutePath();
        fThread.start();
    }

    @Override
    public void run() {
        enableIncomingCommunication();
    }

    // Start the listening loop of client messages
    public void enableIncomingCommunication()
    {
        if (!fAcceptClientCommunication) {
            fAcceptClientCommunication = true;
            while (fAcceptClientCommunication) {
                Message message = fCommunicationHandler.receiveMessage();
                if (message.dType == Message.Type.COMMAND) {
                    CommandLogger.logCommand((Command) message, fSocket);
                    CommandHandler command = CommandHandler.instantiate(((Command) message), this);

                    try {
                        command.execute(fCurrentWorkingDirectory);
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    // Stop listening to incoming messages
    public void disableIncomingCommunication()
    {
        fAcceptClientCommunication = false;
    }
}
