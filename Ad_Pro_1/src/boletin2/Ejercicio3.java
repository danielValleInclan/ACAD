package boletin2;

import java.io.*;
import java.util.Arrays;

public class Ejercicio3 {
    public static void main(String[] args) {
        File file = new File("numPrimos.txt");
        int[] numPrimos = new int[20];
        rellenarPrimos(numPrimos);

        try (BufferedWriter bw = new BufferedWriter(new BufferedWriter(new FileWriter(file)))){
            String line = Arrays.toString(numPrimos);
            bw.write(line);
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
    public static boolean esPrimo(int num){
        if (num == 0 || num == 1 || num == 4){
            return false;
        }
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0){
                return false;
            }
        }
        return true;
    }
    public static void rellenarPrimos(int[] numPrimos){
        int num = 1;
        for (int i = 0; i < numPrimos.length; i++) {
            while (!esPrimo(num)){
                num++;
            }
            if (esPrimo(num)){
                numPrimos[i] = num;
            }
            num++;
        }
    }
}
