package com.accesoadatos;

import com.accesoadatos.andaluciaskills.dao.*;
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
        ExpertoDao expertoDao = new ExpertoDao();

        // Primera consulta
        System.out.println("*** Consulta 1 ***");
        List<String> centros = competidorDao.getCentros();
        System.out.println(centros);

        // Segunda consulta

        System.out.println("*** Consulta 2 ***");
        List<Object[]> puntuaciones = puntuacionPruebaDao.getPuntuacionesPruebaCompetidor();
        System.out.println("Puntuacion  Prueba  Competidor");
        for (Object[] puntuacion : puntuaciones) {
            for (Object valor : puntuacion) {
                System.out.print(valor + "  ");
            }
            System.out.println();  // Salto de línea entre cada conjunto de valores
        }


        // tercera consulta
        System.out.println("*** Consulta 3 ***");
        List<Object[]> usuairosExpertos = expertoDao.getUsernamesDeExpertos();
        for (Object[] resultado : usuairosExpertos) {
            String nombreExperto = (String) resultado[0];
            String usernameUsuario = (String) resultado[1];

            System.out.println("Nombre Experto: " + nombreExperto + ", Username Usuario: " + usernameUsuario);
        }
    }
}
