import java.io.File;

public class T4BoletinFile {
    public static void main(String[] args) {
        File listador = new File("/home/daniel/Escritorio/");
        listaDirectorios(listador, 0);
    }
    public static boolean listaDirectorios(File file, int cont){
        cont++;
        String[] listado = file.list();
        if (listado.length == 0){
            System.out.println("No hay más elementos");
            return true;
        }   else {
            for (String s : listado) {
                for (int i = 0; i < cont; i++) {
                    System.out.print("*");
                }
                System.out.println(s);
                File auxfile = new File(file, s);
                if (auxfile.isDirectory()) {
                    listaDirectorios(auxfile, cont);
                }
            }
            return true;
        }
    }
}
