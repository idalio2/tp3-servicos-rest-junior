package br.com.junior.tp3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Cliente {

    public enum TipoCliente { PESSOA_FISICA, PESSOA_JURIDICA }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Email @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String telefone;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

    @NotNull
    private LocalDate dataCadastro;
}
