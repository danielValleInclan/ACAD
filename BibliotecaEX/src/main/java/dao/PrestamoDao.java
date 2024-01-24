package dao;

import java.util.List;
import javax.persistence.Query;

import dominio.Prestamo;

public class PrestamoDao extends AbstractDao<Prestamo> {

	public PrestamoDao() {
		setClazz(Prestamo.class);
	}

	public List<Prestamo> getPrestamosNoDevueltos(){
		String qlString=" FROM "+ Prestamo.class.getName() +" WHERE fecha_devolucion.prestamo=null";
		Query query = getEntityManager().createQuery(qlString);

		return query.getResultList();
	}
	
}