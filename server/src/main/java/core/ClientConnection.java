package core;

import command.Command;
import communication.Message;
import core.command.CommandHandler;

import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class ClientConnection implements Runnable {
    public CommunicationHandler fCommunicationHandler;
    // The CWD this thread is on.
    public Path fCurrentWorkingDirectory;

    // The client socket.
    private Socket fSocket;
    // The new thread.
    private Thread fThread;
    private boolean fAcceptClientCommunication;

    /**
     * Constructor.
     * @param socket The client socket.
     */
    public ClientConnection(Socket socket) {
        fSocket = socket;
        fThread = new Thread(this);
        fCommunicationHandler = new CommunicationHandler(socket);
        fCurrentWorkingDirectory = FileSystems.getDefault().getPath(".").toAbsolutePath();
        fThread.start();
    }

    /**
     * When the thread is started, enable client connections.
     */
    @Override
    public void run() {
        enableIncomingCommunication();
    }

    /**
     * Start the listening loop of client messages.
     */
    public void enableIncomingCommunication()
    {
        if (!fAcceptClientCommunication) {
            fAcceptClientCommunication = true;
            while (fAcceptClientCommunication) {
                // Wait for a client message.
                Message message = fCommunicationHandler.receiveMessage();
                // If the message is a command, log it and try to execute it.
                if (message.dType == Message.Type.COMMAND) {
                    CommandLogger.logCommand((Command) message, fSocket);
                    CommandHandler command = CommandHandler.instantiate(((Command) message), this);

                    try {
                        command.execute(fCurrentWorkingDirectory);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Stop listening to incoming messages
     */
    public void disableIncomingCommunication()
    {
        fAcceptClientCommunication = false;
    }
}
