package communication;

import java.io.Serializable;

public class File extends Message implements Serializable {


    public File()
    {
        super(Type.FILE);
    }
}
