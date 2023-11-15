package boletin2;

import java.io.*;

public class Ejercicio4 {
    public static void main(String[] args) throws ExepcionDir {
        String rutaOrigen = "Ejercicio4.txt";
        String rutaDestino = "";
        copiarArchivo(rutaOrigen, rutaDestino, false);
    }
    public static void copiarArchivo(String origen, String destino, boolean reemplazo)throws ExepcionDir{
        File file1 = new File(origen);
        if (file1.isDirectory()) throw new ExepcionDir("ERROR: El origen es un directorio");
        File file2;
        if (reemplazo){
            file2 = new File(destino);
        } else {
            file2 = new File(destino + file1.getName());
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file1));
                BufferedWriter bw = new BufferedWriter(new FileWriter(file2))) {
            String line;
            while ((line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e){
            e.getMessage();
        }
    }
    public static class ExepcionDir extends Exception{
        public ExepcionDir (String msg){
            super(msg);
        }
    }
}
