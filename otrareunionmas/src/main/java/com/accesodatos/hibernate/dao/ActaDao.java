package com.accesodatos.hibernate.dao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import com.accesodatos.hibernate.dominio.Acta;

public class ActaDao extends AbstractDao<Acta> {

	public ActaDao() {
		setClazz(Acta.class);
	}
	//String qlString=" FROM "+Acta.class.getName() +" a INNER JOIN "+ Reunion.class.getName()+ " r WHERE r.fecha<date_sub(now(), interval 1 day)";
	//?1, ?2,... es para pasar parámetros por posición. Si queremos, podemos 
	//pasarlos por nombre :nombreParametro
	
	//encontrar actas de reuniones pasadas
	public List<Acta> findActasReunionesAntiguas(){
	
		String qlString=" FROM "+Acta.class.getName() +" a WHERE a.reunion.fecha<:ayer";
		
		LocalDateTime ayer=LocalDateTime.now().minusDays(1);
		//o, dependiendo de lo que entendamos por reuniones pasadas, podría ser también:
		//LocalDateTime ayer= LocalDate.now().atStartOfDay();
		
		Query query = getEntityManager().createQuery(qlString);
		query.setParameter("ayer", ayer);
				
		return query.getResultList();
	}
	
	
	
}
