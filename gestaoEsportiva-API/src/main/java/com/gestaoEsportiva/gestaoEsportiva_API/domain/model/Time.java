package com.gestaoEsportiva.gestaoEsportiva_API.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Column
    private String cidade;

    @OneToMany(mappedBy = "time")
    @Column
    private List<Jogador> jogadores;

}