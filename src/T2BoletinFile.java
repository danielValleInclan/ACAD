import java.io.File;
import java.util.Scanner;

public class T2BoletinFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dir = sc.next();
        File f = new File(dir);
        if (f.isDirectory() || f.isFile()) {
            System.out.println(f.isDirectory() ? "Es un directorio" : f.isFile() ? "Es un fichero" : "");
            System.out.println(f.isAbsolute() ? "Es una ruta absoluta" : "Es una ruta relativa");
            System.out.println(f.getAbsolutePath() + " ruta absoluta");
            System.out.println(f.canWrite() ? "Se puede escribir" : "No se puede escribir");
            System.out.println(f.canRead() ? "Se puede leer" : "No se puede leer");
            System.out.println("Directorio padre: " + f.getParent());
        } else {
            System.out.println("No existe");
        }

    }
}
