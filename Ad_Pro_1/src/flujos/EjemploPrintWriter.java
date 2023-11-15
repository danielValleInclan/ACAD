package flujos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EjemploPrintWriter {
    public static void main(String[] args) throws IOException {
        try (PrintWriter fichero = new PrintWriter(new FileWriter("FichTexto.txt"))){
            for (int i = 0; i < 11; i++) {
                fichero.println("Fila número: " + i);
            }
        }
    }
}
