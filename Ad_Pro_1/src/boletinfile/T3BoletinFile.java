package boletinfile;

import java.io.File;
import java.io.IOException;

public class T3BoletinFile {
    public static void main(String[] args) throws IOException {
        File nuevoDir = new File("NUEVODIR");
        if (nuevoDir.mkdir()){
            System.out.println("Creado correctamente");
        }
        File f1 = new File("NUEVODIR/fichero1");
        File f2 = new File("NUEVODIR/fichero2");
        if (f1.createNewFile() && f2.createNewFile()){
            System.out.println("Creado los ficheros 1 y 2 correctamente");
        }
        if (f1.renameTo(new File("NUEVODIR/fiechero1_Renombrado"))){
            System.out.println("Fichero 1 renombrado correctamente");
        }
        if (f2.delete()){
            System.out.println("Fichero 2 eliminado correctamente");
        }
    }
}
