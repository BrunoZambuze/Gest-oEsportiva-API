package com.gestaoEsportiva.gestaoEsportiva_API.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gestaoEsportiva.gestaoEsportiva_API.api.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Time {

    @NotNull(groups = Groups.CadastroTime.class)
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