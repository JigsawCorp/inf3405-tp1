package communication;

import java.io.Serializable;

public class Info extends Message implements Serializable {
    public enum Status { SUCCESS, FAILURE }
    public enum InfoType { RESPONSE, ACK }

    private String fMessage;
    private Status fStatus;
    private InfoType fInfoType;


    public Info(String message, InfoType infoType, Status status)
    {
        super(Type.INFO);
        fMessage = message;
        fStatus = status;
        fInfoType = infoType;
    }

    @Override
    public String toString()
    {
        return fMessage;
    }
}
