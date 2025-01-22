package com.gestaoEsportiva.gestaoEsportiva_API.domain.repository;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;

import java.util.List;

public interface TimeRepositoryQueries {

    public Time buscarOuFalhar(Long idTime);

    public List<Jogador> buscarJogadoresDoTime(Long idTime);

}
