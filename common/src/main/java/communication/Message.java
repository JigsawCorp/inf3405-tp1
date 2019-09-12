package communication;

import java.io.Serializable;

public abstract class Message implements Serializable {
    public Type dType;

    public enum Type { COMMAND, FILE, INFO }

    protected Message(Type type)
    {
        dType = type;
    }

}
