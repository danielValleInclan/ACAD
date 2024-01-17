package com.accesoadatos;

import com.accesoadatos.andaluciaskills.dao.UsuarioDao;
import com.accesoadatos.andaluciaskills.dominio.Usuario;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UsuarioDao usuarioDao = new UsuarioDao();

        // Muestro todas las reuniones
        List<Usuario> usuarios = usuarioDao.getAll();
        System.out.println("***" + usuarios);

        //añado un usuario

        Usuario usuarioN = new Usuario("Daniel", "Passwd", null);
        usuarioDao.save(usuarioN);

    }
}
