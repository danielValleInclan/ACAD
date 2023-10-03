import java.io.File;

public class T1BoletinFile {
    public static void main(String[] args) {
        String dir = args[0];
        File f = new File(dir);
        if (f.isDirectory()){
            String[] archivos = f.list();
            System.out.printf("Ficheros en el directorio actual: %d %n", archivos.length);
            for (String archivo : archivos) {
                File f2 = new File(f, archivo);
                System.out.printf("Nombre: %s, es fichero?: %b, es directorio?:"
                        + "%b %n", archivo, f2.isFile(), f2.isDirectory());
            }
        }else{
            System.out.println("No es un directorio");
        }
    }
}
