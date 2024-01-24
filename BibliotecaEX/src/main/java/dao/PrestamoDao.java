package dao;

import java.util.List;
import javax.persistence.Query;
import dominio.Prestamo;

public class PrestamoDao extends AbstractDao<Prestamo> {

	public PrestamoDao() {
		setClazz(Prestamo.class);
	}
	
}