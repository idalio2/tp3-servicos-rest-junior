package br.com.junior.tp3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "produtos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Positive(message = "Preço deve ser positivo")
    private Double preco;

    @NotBlank(message = "SKU é obrigatório")
    @Column(unique = true)
    private String sku;

    private boolean ativo = true;
}
