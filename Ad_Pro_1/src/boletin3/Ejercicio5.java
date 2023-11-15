package boletin3;

import java.io.*;
import java.util.Arrays;

public class Ejercicio5 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("imagen.jpg");
        DataInputStream dataIS = new DataInputStream(new FileInputStream(file));
        File file2 = new File("imagen2.jpg");
        Byte[] arrabyte = new Byte[0];
        Byte b;
        try {
            while (true){
                b = dataIS.readByte();
                arrabyte = Arrays.copyOf(arrabyte, arrabyte.length + 1);
                arrabyte[arrabyte.length -1] = b;
            }
        } catch (EOFException ex) {
            System.out.println("Se ha llegado al final del fichero");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(file2));
        try {
            for (Byte i: arrabyte) {
                dataOS.writeByte(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
