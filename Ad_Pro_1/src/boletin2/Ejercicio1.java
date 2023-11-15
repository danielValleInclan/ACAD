package boletin2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio1 {
    public static void main(String[] args) {
        StringBuilder nombreFich = new StringBuilder();
        for (String arg: args) {
            nombreFich.append(arg);
        }
        File fich = new File(nombreFich.toString());
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
