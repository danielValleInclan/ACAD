package tareaOpcional;

import java.io.File;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        File prueba1 = new File("Prueba1");
        File prueba2 = new File("Prueba2");
        DiffFolder diffFolder = new DiffFolder();
        try {
            diffFolder.setFolders(prueba1, prueba2);
        } catch (GestionFicherosExeption exeption){
            System.err.println("Error: " + exeption.getMessage());
            return;
        }
        // Realizar la comparación y obtener el iterador de resultados
        Iterator<ResultadoComparacion> resultados = diffFolder.compare();

        // Iterar a través de los resultados e imprimirlos
        while (resultados.hasNext()) {
            ResultadoComparacion resultado = resultados.next();
            System.out.println("Nombre: " + resultado.getNomFichero() + ", Comparación: " + resultado.getValorComparacion());
        }
    }
}
