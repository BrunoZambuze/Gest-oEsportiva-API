package com.gestaoEsportiva.gestaoEsportiva_API.infraestrutura.repository;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.TimeNaoEncontradoException;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.TimeRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TimeRepositoryImpl implements TimeRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Time findByIdOrElseThrowException(Long timeId) {
        Time timeEncontrado = entityManager.find(Time.class, timeId);
        if(timeEncontrado == null){
            throw new TimeNaoEncontradoException(timeId);
        }
        return timeEncontrado;
    }
}
