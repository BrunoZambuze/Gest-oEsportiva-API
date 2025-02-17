package com.gestaoEsportiva.gestaoEsportiva_API.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @Column
    private String nome;

    @NotBlank
    @Column
    private String cidade;

    @OneToMany(mappedBy = "time")
    @Column
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonManagedReference
    private List<Jogador> jogadores;

}