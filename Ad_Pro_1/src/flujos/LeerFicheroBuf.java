package flujos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerFicheroBuf {
    public static void main(String[] args) {
        try (BufferedReader fichero = new BufferedReader(new FileReader("src/flujos/LeerFicheroBuf.java"))) {
            String linea;
            while ((linea = fichero.readLine()) != null){
                System.out.println(linea);
            }
        } catch (FileNotFoundException e){
            System.out.println("No se encuentra el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
    }
}
