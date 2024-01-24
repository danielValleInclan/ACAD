package dao;

import java.util.List;

import javax.persistence.Query;

import dominio.Ejemplar;
import dominio.Libro;
import dominio.Prestamo;

public class EjemplarDao extends AbstractDao<Ejemplar> {

	public EjemplarDao() {
		setClazz(Ejemplar.class);
	}
	
	public List<Ejemplar> getEjemplaresEditorial(String editorial) {
		String qlString=" FROM "+Ejemplar.class.getName() +" WHERE libro.editorial='"+editorial+"'";
		Query query = getEntityManager().createQuery(qlString);
				
		return query.getResultList();
	}

	public List<Ejemplar> getEjemplaresLibrosMasPaginas(int paginas) {
		String qlString = "SELECT e FROM " + Ejemplar.class.getName() + " e WHERE e.libro.paginas > :paginas";
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter("paginas", paginas);

		return query.getResultList();
	}


}