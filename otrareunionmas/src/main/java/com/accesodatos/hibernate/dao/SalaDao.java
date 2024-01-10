package com.accesodatos.hibernate.dao;

import com.accesodatos.hibernate.dominio.Sala;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class SalaDao extends AbstractDao<Sala>{

    public SalaDao(){
        setClazz(Sala.class);
    }
//devuelve la lista de salas de capacidad mayor o igual que la pasada por parámetro
    //?1, ?2,... es para pasar parámetros por posición. Si queremos, podemos pasarlos por nombre :nombreParametro
	/*public List<Sala> findSalasParaNpersonas(int numPersonas){
		String qlString = "FROM "+Sala.class.getName()+" WHERE capacidad >= ?1";
		Query query=getEntityManager().createQuery(qlString);
		query.setParameter(1, numPersonas);
		return query.getResultList();
	}*/

    //otra versión del método anterior mediante búsqueda por criterios
    //el objeto root es una representación de nuestra entidad, y nos servirá
    //para ir construyendo los criterios, pero nosotros, en este caso, solo tenemos
    //uno, que la capacidad sea mayor o igual (ge=greater or equals) que n

    public List<Sala> findSalasParaNpersonas(int numPersonas){
        //Paso 1: crear CriteriaBuilder y CriteriaQuery de los objetos que queramos obtener
        CriteriaBuilder cb= getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Sala> criteriaQuery =cb.createQuery(Sala.class);

        //Paso 2: from + select
        Root<Sala> root=criteriaQuery.from(Sala.class);
        criteriaQuery.select(root);

        //paso 3: where
        //Predicate pred=cb.ge(root.get("capacidad"), numPersonas);
        //criteriaQuery.where(pred);
        criteriaQuery.where(cb.ge(root.get("capacidad"), numPersonas));
        //o podemos unir la select con el where, así:
        //criteriaQuery.select(root).where(cb.ge(root.get("capacidad"), numPersonas));

        //paso 4: createQuery
        Query query=getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }
    //Prueba nueva forma
    public List<Sala> findSalasParaNpersonasNuevaForma(int numPersonas){
        CriteriaBuilder cb= getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Sala> criteriaQuery =cb.createQuery(Sala.class);
        Root<Sala> root=criteriaQuery.from(Sala.class);
        Predicate pred=cb.ge(root.get("capacidad"), numPersonas);
        criteriaQuery.select(root).where(pred);
        Query query=getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    //buscamos las salas con capacidad mayor o igual que numPersonas
    //y menor o igual que numPersonas*2
    public List<Sala> findSalasAdecuadasParaNpersonas(int numPersonas){
        CriteriaBuilder cb= getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Sala> criteriaQuery =cb.createQuery(Sala.class);
        Root<Sala> root=criteriaQuery.from(Sala.class);
        Predicate capacidadMinima=cb.ge(root.get("capacidad"), numPersonas);
        Predicate capacidadMaxima=cb.le(root.get("capacidad"), numPersonas*2);
        Predicate rangoCapacidad=cb.and(capacidadMinima, capacidadMaxima);

        criteriaQuery.select(root).where(rangoCapacidad);
        Query query=getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }
}
