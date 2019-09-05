package core.connection;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Stack;

public class ConnectionHandler {
    private ServerInformation fServerInfo;
    private Socket fSocket;

    public ConnectionHandler(ServerInformation serverInfo) throws UnknownHostException, IOException, ClassNotFoundException
    {
        fServerInfo = serverInfo;
        fSocket = null;
        connectToServer();
    }

    public void connectToServer() throws UnknownHostException, IOException, ClassNotFoundException
    {
        try {
            // Cr�ation d'un socket client vers le serveur. Ici 127.0.0.1 est indicateur que
            // le serveur s'ex�cute sur la machine locale. Il faut changer 127.0.0.1 pour
            // l'adresse IP du serveur si celui-ci ne s'ex�cute pas sur la m�me machine. Le port est 5000.
            fSocket = new Socket(fServerInfo.fIPAdress, fServerInfo.fPort);
            //ObjectOutputStream objectOutput = new ObjectOutputStream(fSocket.getOutputStream());
            // Ici, on suppose que le fichier que vous voulez inverser se nomme text.txt
            //List<String> linesToSend = readFile("text.txt");
            // �criture de l'objet � envoyer dans le output stream. Attention, la fonction
            // writeObject n'envoie pas l'objet vers le serveur! Elle ne fait qu'�crire dans
            // le output stream.
            //objectOutput.writeObject(linesToSend);
            // Envoi des lignes du fichier texte vers le serveur sous forme d'une liste.
            //objectOutput.flush();
            // Cr�ation du input stream, pour recevoir les donn�es trait�es du serveur.
            //ObjectInputStream obj = new ObjectInputStream(clientSocket.getInputStream());
            // Not� bien que la fonction readObject est bloquante! Ainsi, l'ex�cution du
            // client s'arr�te jusqu'� la r�ception du r�sultat provenant du serveur!
            //Stack<String> receivedStack = (Stack<String>) obj.readObject();
            // �criture du r�sultat dans un fichier nomm�e FichierInversee.txt
            //writeToFile(receivedStack, "FichierInversee.txt");
        } finally {
            // Fermeture du socket.
            //clientSocket.close();
        }
    }

    private void closeConnection() throws IOException
    {
        fSocket.close();
    }



}
