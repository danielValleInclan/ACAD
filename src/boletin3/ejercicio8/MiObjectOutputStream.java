package boletin3.ejercicio8;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {
    public MiObjectOutputStream(OutputStream outputStream) throws IOException {
        super(outputStream);
    }
    protected MiObjectOutputStream() throws IOException, SecurityException{
        super();
    }
    protected void writeStreamHeader() throws IOException{
        System.out.println("No meto cabecera");
    }
}
