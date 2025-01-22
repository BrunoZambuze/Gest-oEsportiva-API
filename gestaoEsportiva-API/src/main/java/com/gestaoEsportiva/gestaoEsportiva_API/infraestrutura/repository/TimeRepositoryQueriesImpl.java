package com.gestaoEsportiva.gestaoEsportiva_API.infraestrutura.repository;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.exception.TimeNaoEncontradoException;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.TimeRepository;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.TimeRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeRepositoryQueriesImpl implements TimeRepositoryQueries {

    @Autowired
    @Lazy
    private TimeRepository timeRepository;

    @Override
    public Time buscarOuFalhar(Long idTime) {
        return timeRepository.findById(idTime).orElseThrow(() -> new TimeNaoEncontradoException(idTime));
    }

    @Override
    public List<Jogador> buscarJogadoresDoTime(Long idTime) {
        Time timeEncontrado = timeRepository.findById(idTime).get();
        return timeEncontrado.getJogadores();
    }


}
