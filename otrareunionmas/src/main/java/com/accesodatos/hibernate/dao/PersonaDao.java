package com.accesodatos.hibernate.dao;
import javax.persistence.Query;

import com.accesodatos.hibernate.dominio.Persona;

public class PersonaDao extends AbstractDao<Persona> {

	public PersonaDao() {
		setClazz(Persona.class);
	}
	
	public Persona get(String numEmple) {

		String qlString=" FROM "+Persona.class.getName() +" WHERE numeroEmpleado="+numEmple;
		Query query = getEntityManager().createQuery(qlString).setMaxResults(1);//porque solo queremos un resultado, una Persona
		return (Persona) query.getSingleResult();
		}
}