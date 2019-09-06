import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Stack;

public class ClientConnection {
    private Socket fSocket = null;
    public ClientConnection(Socket socket) {
        fSocket = socket;
    }

    public void start() throws IOException, ClassNotFoundException {
        // Création d'un input stream. Ce stream contiendra les données envoyées par le
        // client.
        ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(fSocket.getInputStream()));
        // La fonction readObject est bloquante! Ainsi, le serveur arrête son exécution
        // et attend la réception de l'objet envoyé par le client!
        List<String> strings = (List<String>) in.readObject();
        Stack<String> stackOfLines = new Stack<String>();
        // Remplissage de la stack avec les lignes. La première ligne entrée sera la
        // dernière à ressortir.
        for (int i = 0; i < strings.size(); i++) {
            stackOfLines.push(strings.get(i));
        }
        // Création du output stream. Ce stream contiendra les données qui seront
        // envoyées au client.
        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(fSocket.getOutputStream()));
        // Écriture des données dans la pile.
        out.writeObject(stackOfLines);
        // Envoi des données vers le client.
        out.flush();
    }
}
