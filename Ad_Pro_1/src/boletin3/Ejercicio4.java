package boletin3;

import java.io.*;

public class Ejercicio4 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Ejercicio2.txt");
        DataInputStream dataIS = new DataInputStream(new FileInputStream(file));
        int numFilas, numColumnas;
        try {
            numFilas = dataIS.readInt();
            numColumnas = dataIS.readInt();
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
