package com.gestaoEsportiva.gestaoEsportiva_API.infraestrutura.repository;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.JogadorNaoEncontradoExpcetion;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.JogadorRepository;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.JogadorRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JogadorRepositoryImpl implements JogadorRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Jogador findByIdOrElseThrowExpcetion(Long jogadorId) {
        Jogador jogadorEcontrado = entityManager.find(Jogador.class, jogadorId);
        if(jogadorEcontrado == null){
            throw new JogadorNaoEncontradoExpcetion(jogadorId);
        }
        return jogadorEcontrado;
    }
}
