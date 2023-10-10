package boletin2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1 {
    public static void main(String[] args) {
        String nombreFich = "";
        for (String arg: args) {
            nombreFich += arg;
        }
        File fich = new File(nombreFich);
        try (FileReader fr = new FileReader(fich)) {
            int i;
            while ((i = fr.read()) != -1){
                System.out.print((char) i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
