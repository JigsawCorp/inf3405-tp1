package communication;

import java.io.Serializable;

public class Info extends Message implements Serializable {
    private String fMessage;

    public Info(String message)
    {
        super(Type.INFO);
        fMessage = message;
    }

    @Override
    public String toString()
    {
        return fMessage;
    }
}
