package flujos;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EjemploFileReader {
    public static void main(String[] args)  {
        File f = new File("src/flujos/EjemploFileReader.java");
        try (FileReader in = new FileReader(f)) {
            int i;
            while ((i = in.read()) != -1){
                System.out.print((char) i);
            }
        } catch (Exception  e){
            System.out.println(e);
        }
    }
}
