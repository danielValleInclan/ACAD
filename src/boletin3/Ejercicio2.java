package boletin3;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        File file = new File(args[0]);
        double[][] arrayDouble = crearArrayBiDouble(4, 2);
        escribirFichero(arrayDouble, file);
        leerFichero(file);
    }
    public static double[][] crearArrayBiDouble(int filas, int columnas){
        Scanner sc = new Scanner(System.in);
        double[][] arrayDouble = new double[filas][columnas];
        double num = 0;
        int iFilas = 0, iColumnas = 0;
        do {
            System.out.println("Introduce un número con decimanles: (-1 para terminar)");
            try {
                num = sc.nextDouble();
            } catch (Exception e){
                e.getMessage();
            }
            if (num == 0){
                System.out.println("Ingresa un número");
            } else if (num == -1) {
                System.out.println("Saliendo del programa");
            } else {
                arrayDouble[iFilas][iColumnas] = num;
            }
            if (iColumnas == columnas -1){
                iColumnas = 0;
                iFilas++;
            } else {
                iColumnas++;
            }
        } while (iFilas < filas);
        return arrayDouble;
    }
    public static void escribirFichero(double[][] arrayDouble, File file){
        int numFilas = arrayDouble.length;
        int numColumnas = arrayDouble[0].length;
        try (DataOutputStream dataOS = new DataOutputStream(new FileOutputStream(file))){
            dataOS.writeInt(numFilas);
            dataOS.writeInt(numColumnas);
            dataOS.writeUTF("\n");
            for (double[] doubles : arrayDouble) {

                for (double num: doubles) {
                    dataOS.writeDouble(num);
                }
                dataOS.writeUTF("\n");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void leerFichero(File file){
        try (DataInputStream dataIS = new DataInputStream(new FileInputStream(file))){

            int numFilas = dataIS.readInt();
            int numColumnas = dataIS.readInt();
            dataIS.readUTF();
            System.out.println(numFilas +"  " + numColumnas);
            double[][] arrayDouble = new double[numFilas][numColumnas];

            for (int i = 0; i < numFilas; i++) {
                for (int j = 0; j < numColumnas; j++) {
                    arrayDouble[i][j] = dataIS.readDouble();
                }
                dataIS.readUTF(); // Lee el salto de línea
            }

            // Ahora tienes los datos en arrayDouble y puedes trabajar con ellos
            // Por ejemplo, imprimirlos en la consola
            for (int i = 0; i < numFilas; i++) {
                for (int j = 0; j < numColumnas; j++) {
                    System.out.print(arrayDouble[i][j] + " ");
                }
                System.out.println(); // Salto de línea al final de cada fila
            }
        } catch (IOException e){
            e.getMessage();
        }
    }
}
