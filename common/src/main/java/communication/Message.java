package communication;

import java.io.Serializable;

/**
 * A message to send between the client and server.
 */
public abstract class Message implements Serializable {
    // Possible message types.
    public enum Type { COMMAND, INFO }

    // The type of message
    public Type dType;

    /**
     * Constructor.
     * @param type The message type.
     */
    protected Message(Type type)
    {
        dType = type;
    }
}
