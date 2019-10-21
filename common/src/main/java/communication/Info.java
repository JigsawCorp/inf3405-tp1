package communication;

import java.io.Serializable;

/**
 * Type of message used to send error or success messages or acknowledgements.
 */
public class Info extends Message implements Serializable {
    // Status of the Info. Either good or bad.
    public enum Status { SUCCESS, FAILURE }
    // Type of Info. Either a response or ack.
    public enum InfoType { RESPONSE, ACK }

    // The message to send. Usually for displaying to user.
    public String fMessage;
    // Status of the Info.
    public Status fStatus;
    // Type of the Info.
    public InfoType fInfoType;

    /**
     * Constructor.
     * @param message The message of the Info.
     * @param infoType The type of Info.
     * @param status The Info status.
     */
    public Info(String message, InfoType infoType, Status status)
    {
        super(Type.INFO);
        fMessage = message;
        fStatus = status;
        fInfoType = infoType;
    }

    /**
     * Get a String representation of the Info.
     * @return A formatted string.
     */
    @Override
    public String toString()
    {
        return fMessage;
    }
}
