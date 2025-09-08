package br.com.junior.tp3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "fornecedores")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Fornecedor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Razão social é obrigatória")
    private String razaoSocial;

    @NotBlank(message = "Documento fiscal é obrigatório")
    @Column(unique = true)
    private String documentoFiscal; // genérico (serve p/ CNPJ/NIF/VAT)

    @Email @NotBlank
    private String email;

    @NotBlank
    private String telefone;
}
