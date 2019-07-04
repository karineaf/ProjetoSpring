package org.generation.brazil.gfood.produto;
import org.generation.brazil.gfood.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNome(String nome);

    List<Produto> findByPrecoLessThan(BigDecimal precinho);

    List<Produto> findByPrecoGreaterThan(BigDecimal precinho);

    List<Produto> findByPrecoBetween(BigDecimal precinho, BigDecimal precao);

    @Modifying
    @Query("delete from Produto p where p.preco < ?1")
    void deleteProdutoByIdIsLessThan(BigDecimal precoComparativo);

}


