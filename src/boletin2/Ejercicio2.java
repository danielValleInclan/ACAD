package boletin2;

import java.io.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        File file = new File("numNaturales.txt");
        try (BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(file)))){
            for (int i = 0; i < 100; i++) {
                bw.write(i + "");
                bw.newLine();
            }
        } catch (IOException e){
            e.getMessage();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch(IOException e) {
            e.getMessage();
        }
    }
}
