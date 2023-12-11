package tareadb4oempdep;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.Arrays;

public class Main {

    final static String DBEmpDep = "/home/daniel/Escritorio/acad/EMPLEDEP.yap";
    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DBEmpDep);

        Empleado e1 = new Empleado("Juan");
        Empleado e2 = new Empleado("Maria");
        Departamento d1 = new Departamento("Ventas", Arrays.asList(e1, e2));

        db.store(e1);
        db.store(e2);
        db.store(d1);

        ObjectSet<Departamento> result = db.queryByExample(new Departamento());

        while (result.hasNext()){
            Departamento departamento = result.next();
            System.out.println("Departamento: " + departamento.getNombre());

            for (Empleado empleado : departamento.getEmpleados()){
                System.out.println(" Empleado: " + empleado.getNombre());
            }
        }

        db.close();
    }
}
