package com.projetos.cursos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.projetos.cursos.model.Cursos;

public class CursosService {

    @PersistenceContext
    EntityManager em;

    public List<Cursos> filtrar(String descricao, LocalDate inicio, LocalDate termino) {
     
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Cursos> criteriaQuery = criteria.createQuery(Cursos.class);

        Root<Cursos> cursos = criteriaQuery.from(Cursos.class);
        List<Predicate> predList = new ArrayList<>();

        if (descricao != null && descricao!= "" ) {
            Predicate descricaoPredicate = criteria.equal(cursos.get("descricao"), descricao);
            predList.add(descricaoPredicate);
        }

        if (inicio != null) {
            Predicate inicioPredicate = criteria.greaterThanOrEqualTo(cursos.get("inicio"), inicio);
            predList.add(inicioPredicate);
        }

        if (termino != null) {
            Predicate terminoPredicate = criteria.lessThanOrEqualTo(cursos.get("termino"), termino);
            predList.add(terminoPredicate);
        }

        Predicate[] predicateArray = new Predicate[predList.size()];

        predList.toArray(predicateArray);

        criteriaQuery.where(predicateArray);

        TypedQuery<Cursos> query = em.createQuery(criteriaQuery);

        return query.getResultList();

    }
}
