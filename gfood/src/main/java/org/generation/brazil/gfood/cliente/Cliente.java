package org.generation.brazil.gfood.cliente;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data // p gerar get e sets (depois de instalar o lombok)
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // p/ que faça o auto incremento
    private Long id;

    @NotNull
    private String nome;
    private String endereco;


    @NotNull
    @Column(name = "data_nasc")
    private Date dataNasc;
}
