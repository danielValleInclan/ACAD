package ejemplos.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Main {
    final static String BDPer = "/home/daniel/Escritorio/acad/personas.yap";
    public static void main(String[] args) {

        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDPer);

        Persona p1 = new Persona("Fernando", "Palencia");
        Persona p2 = new Persona("David", "Sevilla");
        Persona p3 = new Persona("Torres", "Madrid");
        Persona p4 = new Persona("Villa", "Barcelona");
        Persona p5 = new Persona("Pedri", "Málaga");


        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);
        db.store(p5);

        Persona p = new Persona();

        ObjectSet<Persona> result = db.queryByExample(p);
        if (result.size() == 0)
            System.out.println("No existen Registros de Personas...");
        else {
            System.out.printf("Número de registros: %d %n", result.size());

            while (result.hasNext()){
                System.out.println(result.next());
            }
        }

        db.close();
    }
}
