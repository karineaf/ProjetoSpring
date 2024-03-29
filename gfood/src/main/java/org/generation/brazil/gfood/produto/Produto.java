package org.generation.brazil.gfood.produto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // p/ que faça o auto incremento
    private Long id;

    @NotNull
    private String nome;
    private String descricao;

    @NotNull
    private BigDecimal preco;

}
