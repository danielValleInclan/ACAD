package boletin3;

import java.io.*;

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        File file = new File("datos.dat");
        DataInputStream dataIS = new DataInputStream(new FileInputStream(file));

        int e;
        try {
            while (true){
                e = dataIS.readInt();
                System.out.println("Número -> " + e);
            }
        } catch (EOFException ex) {
            System.out.println("Se ha llegado al final del fichero");
        }
    }
}
