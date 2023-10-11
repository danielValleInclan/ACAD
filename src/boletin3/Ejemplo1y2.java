package boletin3;

import java.io.*;

public class Ejemplo1y2 {
    public static void main(String[] args) throws IOException {
        File file = new File("FichData.dat");
        escritura(file);
        lectura(file);
    }

    public static void lectura(File file) throws IOException{
        DataInputStream dataIS = new DataInputStream(new FileInputStream(file));
        String n;
        int e;

        try {
            while (true){
                dataIS.readUTF();
                dataIS.readInt();
            }
        } catch (IOException exception){
            exception.getMessage();
        }
        dataIS.close();
    }
    public static void escritura(File file) throws IOException{
        DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(file));
        String[] nombres = {"Ana", "Luis Miguel", "Alicia", "Pedro", "Manuel"};
        int[] edades = {14, 15, 13, 15, 16};
        for (int i = 0; i < edades.length; i++) {
            dataOS.writeUTF(nombres[i]);
            dataOS.writeInt(edades[i]);
        }
        dataOS.close();
    }

}
