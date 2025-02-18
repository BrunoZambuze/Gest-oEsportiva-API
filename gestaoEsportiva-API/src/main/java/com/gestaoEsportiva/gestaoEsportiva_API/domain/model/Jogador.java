package com.gestaoEsportiva.gestaoEsportiva_API.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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
    @PastOrPresent
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull
    @Valid
    @OneToOne
    @JsonBackReference
    private Time time;

}
