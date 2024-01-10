package com.accesodatos.hibernate.dao;

import com.accesodatos.hibernate.dominio.Persona;
import com.accesodatos.hibernate.dominio.Reunion;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class ReunionDao extends AbstractDao<Reunion> {
    public ReunionDao(){
        setClazz(Reunion.class);
    }

    @Override
    public Optional<Reunion> get(int id) {
        return super.get(id);
    }

    @Override
    public List<Reunion> getAll() {
        return super.getAll();
    }

    @Override
    public void save(Reunion reunion) {
        super.save(reunion);
    }

    @Override
    public void update(Reunion reunion) {
        super.update(reunion);
    }

    @Override
    public void delete(Reunion reunion) {
        super.delete(reunion);
    }

    @Override
    public void setClazz(Class<Reunion> clazz) {
        super.setClazz(clazz);
    }

    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    //además de los métodos que heredamos de AbstractDao, seguramente queramos crear otras querys para esta tabla particularmente:

    //por ejemplo:
    //recuperar próxima reunión
    public Reunion getProximaReunion() {
        String qlString=" FROM "+Reunion.class.getName() +" WHERE fecha> now() ORDER BY fecha";
        Query query = getEntityManager().createQuery(qlString).setMaxResults(1);//porque solo queremos un resultado, una Reunion
        return (Reunion) query.getSingleResult();
    }

    public List<Reunion> getReunionesManyana() {
        String qlString=" FROM "+Reunion.class.getName() +" WHERE fecha BETWEEN ?1 AND ?2";
        Query query = getEntityManager().createQuery(qlString);

        LocalDate manyana=LocalDate.now().plus(1, ChronoUnit.DAYS);
        query.setParameter(1, manyana.atStartOfDay());
        query.setParameter(2, manyana.plus(1, ChronoUnit.DAYS).atStartOfDay());

        return query.getResultList();
    }


    //criteriaQuery.multiselect(joinReunion).where(cb.equal(fromPersona.get("numeroEmpleado"), numEmple));
    //ver en https://www.initgrep.com/posts/java/jpa/select-values-in-criteria-queries

    public List<Reunion> reunionesParticipante(String numEmple){
        CriteriaBuilder cb= getEntityManager().getCriteriaBuilder();
        //Reunion porque quiero recuperar objetos reunión. Si por ejemplo fuera a hacer un count,
        //pondría CriteriaQuery<Long> y Long.class
        CriteriaQuery<Reunion> criteriaQuery =cb.createQuery(Reunion.class);

        Root<Persona> fromPersona=criteriaQuery.from(Persona.class);

        Join<Persona, Reunion> joinReunion = fromPersona.join("reuniones", JoinType.INNER);

        criteriaQuery.select(joinReunion).where(cb.equal(fromPersona.get("numeroEmpleado"), numEmple));
        TypedQuery<Reunion> query=getEntityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

}
