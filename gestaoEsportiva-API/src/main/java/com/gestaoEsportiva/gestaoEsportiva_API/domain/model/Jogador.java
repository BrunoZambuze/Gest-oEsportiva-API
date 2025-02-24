package com.gestaoEsportiva.gestaoEsportiva_API.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gestaoEsportiva.gestaoEsportiva_API.api.core.validation.DataValida;
import com.gestaoEsportiva.gestaoEsportiva_API.api.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Column
    private String nome;

    @NotNull
    @DataValida
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Valid
    @NotNull
    @ConvertGroup(from = Default.class, to = Groups.jogadorId.class)
    @OneToOne
    @JsonBackReference
    private Time time;

}
