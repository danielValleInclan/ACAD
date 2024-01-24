package dao;

import java.util.List;

import javax.persistence.Query;

import dominio.Ejemplar;

public class EjemplarDao extends AbstractDao<Ejemplar> {

	public EjemplarDao() {
		setClazz(Ejemplar.class);
	}
	
	public List<Ejemplar> getEjemplaresEditorial(String editorial) {
		String qlString=" FROM "+Ejemplar.class.getName() +" WHERE libro.editorial='"+editorial+"'";
		Query query = getEntityManager().createQuery(qlString);
				
		return query.getResultList();
	}
	
}