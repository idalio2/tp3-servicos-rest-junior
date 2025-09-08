package br.com.junior.tp3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Cargo é obrigatório")
    private String cargo;

    @Positive(message = "Salário deve ser positivo")
    private Double salario;

    @NotNull(message = "Data de admissão é obrigatória")
    private LocalDate dataAdmissao;

    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    @Column(unique = true)
    private String email;
}
