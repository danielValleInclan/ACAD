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
		// Creando autores
		Autor autor1, autor2;
		autor1 = new Autor("Autor 1");
		autor2 = new Autor("Autor 2");

		autorDao.save(autor1);
		autorDao.save(autor2);

		System.out.println(autorDao.getAll());

		// Creando libro
		Libro libro = new Libro("Libro 1", "isbn 1", "Editorial 1", 90);

		autor1.addLibro(libro);
		autor2.addLibro(libro);

		libro.addAutor(autor1);
		libro.addAutor(autor2);


		libroDao.save(libro);

	}
}