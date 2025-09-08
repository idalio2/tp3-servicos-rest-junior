package br.com.junior.tp3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "projetos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Projeto {

    public enum StatusProjeto { PLANEJADO, EM_ANDAMENTO, CONCLUIDO }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFimPrevista;
}
