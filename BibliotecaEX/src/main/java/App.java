import java.time.LocalDateTime;
import java.util.Optional;

import dao.AutorDao;
import dao.EjemplarDao;
import dao.LibroDao;
import dao.PrestamoDao;
import dao.UsuarioDao;
import dominio.Autor;
import dominio.Ejemplar;
import dominio.Libro;
import dominio.Prestamo;
import dominio.Usuario;

public class App {

	public static void main(String[] args) {
		AutorDao autorDao = new AutorDao();
		LibroDao libroDao = new LibroDao();
		EjemplarDao ejemplarDao = new EjemplarDao();
		UsuarioDao usuarioDao = new UsuarioDao();
		PrestamoDao prestamoDao = new PrestamoDao();

		// Limpar bd
		for (Prestamo p : prestamoDao.getAll()) prestamoDao.delete(p);
		for (Autor a : autorDao.getAll()) autorDao.delete(a);
		for (Usuario u: usuarioDao.getAll()) usuarioDao.delete(u);
		for (Ejemplar e : ejemplarDao.getAll()) ejemplarDao.delete(e);
		for (Libro l : libroDao.getAll()) libroDao.delete(l);

		/*
		* Primera acción
		* */
		Autor autor1, autor2;
		autor1 = new Autor("Autor 1");
		autor2 = new Autor("Autor 2");

		// Creando libro
		Libro libro = new Libro("Libro 1", "isbn 1", "Editorial 1", 90);

		autorDao.save(autor1);
		autorDao.save(autor2);
		autor1.addLibro(libro);
		autor2.addLibro(libro);
		libroDao.save(libro);

		System.out.println("Creados autores y libro");
		System.out.println();

		/*
		*
		* Segunda Acción
		*
		* */
		// Creamos los ejemplares

		Ejemplar ejemplar1 = new Ejemplar("Codigo 1", "Biblioteca 1", libro);
		Ejemplar ejemplar2 = new Ejemplar("Codigo 2 ", "Biblioteca 2", libro);

		ejemplarDao.save(ejemplar1);
		ejemplarDao.save(ejemplar2);

		System.out.println("Creados ejemplares");
		System.out.println();

		/*
		*
		* Tercera acción
		*
		* */



		Usuario usuario1 = new Usuario("Usuario 1");
		Usuario usuario2 = new Usuario("Usuario 2");

		usuarioDao.save(usuario1);
		usuarioDao.save(usuario2);

		System.out.println("Creados usuarios");


		Prestamo prestamoUsuario1 = new Prestamo(usuario1, ejemplar1);
		System.out.println("Préstamo a Usuario1: " + prestamoUsuario1);

		LocalDateTime fechaPrestamo = LocalDateTime.now();
		LocalDateTime fechaDevolucion = fechaPrestamo.plusDays(7);
		Prestamo prestamoUsuario2 = new Prestamo(usuario2, ejemplar2, fechaPrestamo, fechaDevolucion);
		System.out.println("Préstamo a Usuario2: " + prestamoUsuario2);

		prestamoDao.save(prestamoUsuario1);
		prestamoDao.save(prestamoUsuario2);

		System.out.println("Prestamos volcados a la BD");
		System.out.println();

		/*
		*
		* Cuarta acción
		*
		* */

		System.out.println("Recuperando un Objeto de la bd ...");
		Prestamo prestamoRescatado = prestamoDao.getAll().get(1);
		System.out.println(prestamoRescatado);
		System.out.println();

		/*
		*
		* Quinta acción
		*
		* */

		prestamoDao.delete(prestamoRescatado);
		System.out.println("Borrado");


		/*
		*
		* Sexta acción
		*
		* */

		System.out.println("Prestamos no devueltos: " + prestamoDao.getPrestamosNoDevueltos());

		/*
		*
		* Septima opcion
		*
		* */

		System.out.println("Ejemplares de mas de 30 páginas: " + ejemplarDao.getEjemplaresLibrosMasPaginas(30));
	}
}