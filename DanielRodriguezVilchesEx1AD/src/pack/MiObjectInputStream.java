package pack;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectInputStream extends ObjectOutputStream {
    public MiObjectInputStream (OutputStream out) throws IOException {
        super(out);
    }

    public MiObjectInputStream() throws IOException, SecurityException {
        super();
    }

    protected void writeStreamHeader() throws IOException{
        System.out.println("No meto la cabecera");
    }
}
