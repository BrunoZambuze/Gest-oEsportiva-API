package com.gestaoEsportiva.gestaoEsportiva_API.domain.service;

import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Jogador;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.model.Time;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.JogadorRepository;
import com.gestaoEsportiva.gestaoEsportiva_API.domain.repository.TimeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    public Time salvar(Time time){
        return timeRepository.save(time);
    }

    public List<Jogador> buscarJogadores(Long timeId){
        timeRepository.buscarOuFalhar(timeId);
        return timeRepository.buscarJogadoresDoTime(timeId);
    }

    public void atualizar(Time timeAtualizar, Long timeId){
        Time timeEncontrado = timeRepository.buscarOuFalhar(timeId);
        BeanUtils.copyProperties(timeAtualizar, timeEncontrado, "id");
        timeRepository.save(timeEncontrado);
    }

    public void deletar(Long timeId){
        Time timeEncontrado = timeRepository.buscarOuFalhar(timeId);
        timeRepository.delete(timeEncontrado);
    }

}
