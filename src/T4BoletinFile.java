import java.io.File;

public class T4BoletinFile {
    public static void main(String[] args) {
        File listador = new File("/home/daniel/Escritorio/");
        listaDirectorios(listador, "*");
    }
    public static boolean listaDirectorios(File file, String separador){
        File[] listado = file.listFiles();
        if (listado.length == 0){
            System.out.println("No hay más elementos");
            return true;
        }   else {
            for (File s : listado) {
                separador += "*";
                System.out.println(separador + s.getName());
                if (s.isDirectory()) {
                    listaDirectorios(s, separador);
                }
            }
            return true;
        }
    }
}
