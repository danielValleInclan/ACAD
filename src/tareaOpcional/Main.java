package tareaOpcional;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File prueba1 = new File("Prueba1");
        File prueba2 = new File("Prueba2");
        DiffFolder diffFolder = new DiffFolder();
        try {
            diffFolder.setFolders(prueba1, prueba2);
        } catch (GestionFicherosExeption exeption){
            System.out.println(exeption);
        }
    }
}
