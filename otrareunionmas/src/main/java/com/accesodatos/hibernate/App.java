package com.accesodatos.hibernate;

import com.accesodatos.hibernate.dao.ActaDao;
import com.accesodatos.hibernate.dao.PersonaDao;
import com.accesodatos.hibernate.dao.ReunionDao;
import com.accesodatos.hibernate.dao.SalaDao;
import com.accesodatos.hibernate.dominio.Acta;
import com.accesodatos.hibernate.dominio.Persona;
import com.accesodatos.hibernate.dominio.Reunion;
import com.accesodatos.hibernate.dominio.Sala;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
        System.out.println( "Empezamos" );
        //1-trabajamos sobre reuniones
        ReunionDao reunionDao = new ReunionDao();

        // muestro todas las reuniones
        List<Reunion> reuniones = reunionDao.getAll();
        System.out.println(" *** " + reuniones);

        // anado una reunion - como hemos marcado la pk de reunion como
        // autoincremental (IDENTITY),
        // al guardar y luego mostrar, se ve que te ha generado el id
        Reunion rNueva = new Reunion(LocalDateTime.now().plus(2, ChronoUnit.DAYS), "Reunion de pasado mañana");
        System.out.println(rNueva);
        reunionDao.save(rNueva);
        System.out.println(rNueva);

        //2-trabajamos con personas
        PersonaDao personaDao = new PersonaDao();

        //mostrar todas las personas
        List<Persona> personas = personaDao.getAll();
        System.out.println(" *** " + personas);

        // añadir una nueva

        Persona personaNueva = new Persona(1000, "Daniel", "Rodríguez");
        System.out.println(personaNueva);
        personaDao.save(personaNueva);
        System.out.println(personaNueva);

        //3- Eliminar una reunión
        reunionDao.delete(rNueva);

    }
}
