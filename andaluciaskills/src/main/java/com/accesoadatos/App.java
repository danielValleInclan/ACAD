package com.accesoadatos;

import com.accesoadatos.andaluciaskills.dao.CompetidorDao;
import com.accesoadatos.andaluciaskills.dao.PruebaDao;
import com.accesoadatos.andaluciaskills.dao.PuntuacionPruebaDao;
import com.accesoadatos.andaluciaskills.dao.UsuarioDao;
import com.accesoadatos.andaluciaskills.dominio.Competidor;
import com.accesoadatos.andaluciaskills.dominio.Prueba;
import com.accesoadatos.andaluciaskills.dominio.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UsuarioDao usuarioDao = new UsuarioDao();
        CompetidorDao competidorDao = new CompetidorDao();
        PruebaDao pruebaDao = new PruebaDao();
        PuntuacionPruebaDao puntuacionPruebaDao = new PuntuacionPruebaDao();

        // Primera consulta
        List<String> centros = competidorDao.getCentros();
        System.out.println(centros);

        // Segunda consulta

        List<Object[]> puntuaciones = puntuacionPruebaDao.getPuntuacionesPruebaCompetidor();
        System.out.println("Puntuacion  Prueba  Competidor");
        for (Object[] puntuacion : puntuaciones) {
            for (Object valor : puntuacion) {
                System.out.print(valor + "  ");
            }
            System.out.println();  // Salto de línea entre cada conjunto de valores
        }


    }
}
