package com.gestaoEsportiva.gestaoEsportiva_API.domain.service;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.JogadorRepository;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.TimeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeRepository timeRepository;

    public Jogador salvar(Jogador jogador){
        Long timeId = jogador.getTime().getId();
        Time timeEncontrado = timeRepository.findByIdOrElseThrowException(timeId);
        jogador.setTime(timeEncontrado);
        Jogador jogadorSalvo = jogadorRepository.save(jogador);
        return jogadorSalvo;
    }

    public void deletar(Long jogadorId){
        Jogador jogadorEncontrado = jogadorRepository.findByIdOrElseThrowExpcetion(jogadorId);
        jogadorRepository.delete(jogadorEncontrado);
    }

    public Jogador atualizar(Long jogadorId, Jogador jogadorAlterar){
        Jogador jogadorEncontrado = jogadorRepository.findByIdOrElseThrowExpcetion(jogadorId);
        Long timeId = jogadorEncontrado.getTime().getId();
        Time timeEncontrado = timeRepository.findByIdOrElseThrowException(timeId);
        BeanUtils.copyProperties(jogadorAlterar, jogadorEncontrado, "id");
        return jogadorRepository.save(jogadorEncontrado);
    }

}