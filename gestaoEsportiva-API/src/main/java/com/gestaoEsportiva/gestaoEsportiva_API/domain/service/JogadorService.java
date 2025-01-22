package com.gestaoEsportiva.gestaoEsportiva_API.domain.service;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.JogadorRepository;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeRepository timeRepository;

    public Jogador salvar(Jogador jogador){
        Long idTime = jogador.getTime().getId();
        Time timeEncontrado = timeRepository.buscarOuFalhar(idTime);
        jogador.setTime(timeEncontrado);
        return jogadorRepository.save(jogador);
    }

    public void deletar(Long jogadorId){

    }

}
