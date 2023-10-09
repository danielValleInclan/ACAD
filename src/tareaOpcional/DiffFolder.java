package tareaOpcional;

import java.io.File;
import java.util.*;

public class DiffFolder {
    private File folder1;
    private File folder2;

    public void setFolders(File folder1, File folder2) throws GestionFicherosExeption{
        if (!folder1.exists() || !folder2.exists())
            throw new GestionFicherosExeption("Uno o ambos directorios no existe");
        if (!folder1.isDirectory() || !folder2.isDirectory())
            throw new GestionFicherosExeption("Uno o ambos directorios no es un directorio");
        this.folder1 = folder1;
        this.folder2 = folder2;
    }

    public File getFolder1() {
        return folder1;
    }

    public File getFolder2() {
        return folder2;
    }

    Iterator <ResultadoComparacion> compare(){
        List<ResultadoComparacion> resultados = new ArrayList<>();
        File[] files1 = folder1.listFiles();
        File[] files2 = folder2.listFiles();

        if (files1 != null && files2 != null) {
            for (File file1 : files1) {
                boolean encontrado = false;
                for (File file2 : files2) {
                    if (file1.getName().equals(file2.getName())) {
                        encontrado = true;
                        if (file1.lastModified() == file2.lastModified()) {
                            resultados.add(new ResultadoComparacion(file1.getName(), ValorComparacion.IGUALES));
                        } else if (file1.lastModified() > file2.lastModified()) {
                            resultados.add(new ResultadoComparacion(file1.getName(), ValorComparacion.MENOS_NUEVO_EN_2));
                        } else {
                            resultados.add(new ResultadoComparacion(file1.getName(), ValorComparacion.MENOS_NUEVO_EN_1));
                        }
                        break;
                    }
                }
                if (!encontrado) {
                    resultados.add(new ResultadoComparacion(file1.getName(), ValorComparacion.FALTA_EN_2));
                }
            }

            for (File file2 : files2) {
                boolean encontrado = false;
                for (File file1 : files1) {
                    if (file2.getName().equals(file1.getName())) {
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    resultados.add(new ResultadoComparacion(file2.getName(), ValorComparacion.FALTA_EN_1));
                }
            }
        }

        return resultados.iterator();

    }
}
