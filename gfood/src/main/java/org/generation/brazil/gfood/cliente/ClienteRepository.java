package org.generation.brazil.gfood.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);

    List<Cliente> findByDataNasc(Date data);

    List<Cliente> findByNomeAndDataNasc(String nome, Date data);

    @Transactional
    @Modifying
    void deleteByDataNascAndNome(Date data, String nome);


    @Modifying
    @Query("update Cliente c set c.nome=:nome where c.id=:id")
    void updateNameById(String nome, Long id);





/*
    void deleteByDataNascAndNome(Date data, String nome);
    @Modifying
    @Query(value = "delete from cl")
    void deleteClientesByDataNascAndNome(@Param("dataNasc") Date data, @Param("nome") String nome);
*/

/*    @Modifying
    @Query(value = " *   FROM cliente WHERE id = ?1", nativeQuery = true)
    Cliente findClienteById(Long id);*/
}