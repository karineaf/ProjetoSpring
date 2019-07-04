package org.generation.brazil.gfood.cliente;
import org.generation.brazil.gfood.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {
    @Autowired // para nn ter o nullpointer
    private ClienteRepository repository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/clientes")
    public Cliente save(@RequestBody Cliente cliente){ //@RequestBody converte o q foi feito no jason para um objeto da classe cliente
        //INSERT INTO cliente ... Value ...
        return repository.save(cliente);
    }

    @GetMapping("/clientes")
    public List<Cliente> findAll(){
        //SELECT * FROM cliente
        return repository.findAll();
    }

    @GetMapping("/clientes/{id}")
    public Optional<Cliente> findById(@PathVariable Long id){
        return repository.findById(id);
    }

    @PostMapping("/clientes/nome")
    public List<Cliente> findByNome(@RequestParam String nome){
        return repository.findByNome(nome);
    }

    /*@GetMapping("/clientes/dataNasc")
    public List<Cliente> findByDataNasc(@RequestParam Date data){
        return repository.findByDataNasc(data);
    }
     */

    @PostMapping("/clientes/dataNasc")
    public List<Cliente> findByDataNasc(@RequestParam Date data){
        return repository.findByDataNasc(data);
    }

    @PostMapping("/clientes/nomeAndDataNasc")
    public List<Cliente> findByNomeAndDataNasc(@RequestParam String nome, @RequestParam Date data ){
        return repository.findByNomeAndDataNasc(nome, data);
    }

    @PutMapping("/clientes/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente)
// "UPDATE cliente SET ... WHERE ..."
            throws ResourceNotFoundException{
        return repository.findById(id).map(c -> {
            c.setNome(cliente.getNome());
            c.setEndereco(cliente.getEndereco());
            return repository.save(c);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não existe cliente cadastrado com o id: " + id));
    }

/*    @PutMapping("/clientes/alteraNome/{id}")
    public Cliente alterNomebyId(@PathVariable Long id, @RequestParam String nome)
// "UPDATE cliente SET ... WHERE ..."
            throws ResourceNotFoundException{
        return repository.findById(id).map(n -> {
            n.setNome(nome);
            return repository.save(n);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não existe cliente cadastrado com o id: " + id));
    }*/

    @Transactional
    @PutMapping("/clientes/novoNome/{id}")
    public void updateNameById(@PathVariable Long id, @RequestParam String nome){
        repository.updateNameById(nome, id);
    }

    @DeleteMapping("/clientes/{id}")
    public void delete(@PathVariable Long id){  //o @pathvaruable informa a variável q está no caminho e será "manipulada"
        //DELETE FROM cliente WHERE id="..."
        repository.deleteById(id);
    }


    @DeleteMapping("/clientes/delete")
    public void deleteClientesByDataNascAndAndNome(@RequestParam Date dataNasc, @RequestParam String nome){  //o @pathvaruable informa a variável q está no caminho e será "manipulada"
        //DELETE FROM cliente WHERE id="..."
        repository.deleteByDataNascAndNome(dataNasc, nome);
    }







}
