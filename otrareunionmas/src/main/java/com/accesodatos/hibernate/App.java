package com.accesodatos.hibernate;

import com.accesodatos.hibernate.dao.ReunionDao;
import com.accesodatos.hibernate.dominio.Reunion;

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
        //1-trabajamos sobre funciones
        ReunionDao reunionDao = new ReunionDao();

        // muestro todas las reuniones
        List<Reunion> reuniones = reunionDao.getAll();
        System.out.println("*** " + reuniones);

        // añado una reunión - como hemos marcado la pk reunión como autoincremental (IDENTITY), al guardar
        // y luego mostrar, se ve que te  ha generado el id
        Reunion rNueva = new Reunion(LocalDateTime.now().plus(2,ChronoUnit.DAYS), "Reunión de pasado" +
                "mañana");
        System.out.println(rNueva);
        reunionDao.save(rNueva);
        System.out.println(rNueva);
    }
}
