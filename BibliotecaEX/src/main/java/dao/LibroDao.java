package dao;

import java.util.List;
import javax.persistence.Query;
import dominio.Libro;

public class LibroDao extends AbstractDao<Libro> {

	public LibroDao() {
		setClazz(Libro.class);
	}
	
}