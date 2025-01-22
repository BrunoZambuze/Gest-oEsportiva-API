package com.gestaoEsportiva.gestaoEsportiva_API.domain.repository;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
