package communication;

import java.io.Serializable;

public class Message implements Serializable {
    public Type dType;
    public Object fData;

    enum Type { COMMAND }

    public Message(Type type, Object data)
    {
        dType = type;
        fData = data;
    }

}
