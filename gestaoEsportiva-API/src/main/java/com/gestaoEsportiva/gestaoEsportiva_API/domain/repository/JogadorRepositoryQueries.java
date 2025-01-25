package com.gestaoEsportiva.gestaoEsportiva_API.domain.repository;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;

public interface JogadorRepositoryQueries {

    public Jogador findByIdOrElseThrowExpcetion(Long jogadorId);

}
