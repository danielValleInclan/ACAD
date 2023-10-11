package boletin3;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        File file = new File("datos.dat");
        int[] nums = pedirNumeros(sc);
        escribiendoFichero(file, nums);
    }

    public static void escribiendoFichero(File file, int[] nums) throws FileNotFoundException {
        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
        try {
            for (int i = 0; i < nums.length; i++) {
                dataOutputStream.writeInt(nums[i]);
            }
        } catch (IOException e){
            e.getMessage();
        }
    }
    public static int[] pedirNumeros(Scanner sc){
        int[] nums = new int[0];
        int num = 0;
        do {
            System.out.println("Introduce un número positivo (-1 para salir)");
            try {
                num = sc.nextInt();
            } catch (Exception e){
                e.getMessage();
            }
            if (num < 0 && num != -1){
                System.out.println("No es un número válido");
            } else if (num == -1){
                System.out.println("Saliendo del programa");
            } else {
                nums = Arrays.copyOf(nums, nums.length + 1);
                nums[nums.length - 1] = num;
            }
        } while (num != -1);
        return nums;
    }
}
