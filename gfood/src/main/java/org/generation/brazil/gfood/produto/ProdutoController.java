package org.generation.brazil.gfood.produto;

import org.generation.brazil.gfood.cliente.Cliente;
import org.generation.brazil.gfood.cliente.ClienteRepository;
import org.generation.brazil.gfood.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController // diz ao spring que essa classe faz o papel do controlador (sem isso nn da pra acessa a classe pela internet)
public class ProdutoController {

    @Autowired // fala "cria pra mim um repositório" consequentemente nn tem o erro nullpointer - injeção de dependencia
    private ProdutoRepository repository;


    @ResponseStatus(HttpStatus.CREATED) // é para informar após o procedimento que ele foi criado c sucesso
    @PostMapping("/produtos") //endereço que estará localizado
    public Produto save(@RequestBody Produto produto){ //@RequestBody converte o q foi feito no jason para um objeto da classe produto
        //INSERT INTO produto ... Value ...
        return repository.save(produto);
    }

    @GetMapping("/produtos")
    public List<Produto> findAll(){
        //SELECT * FROM produto
        return repository.findAll();
    }

    @GetMapping("/produtos/{id}")
    public Optional<Produto> findById(@PathVariable Long id){
        // buscar pelo id
        return repository.findById(id);
    }

    @PostMapping("/produtos/nome")
    public List<Produto> findByNome(@RequestParam String nome){
        //buscar pelo nome
        return repository.findByNome(nome);
    }

    /*@PostMapping("/produtos/preco")
    public List<Produto> findByPreco(@RequestParam BigDecimal preco){
        double precoDouble = preco.doubleValue();
        if (precoDouble< 10.00) {
            return repository.findByPreco(preco);
        } else{

        }
    }*/

    @PostMapping("/produtos/precoMenor")
    public List<Produto> findByPrecoLessThan(@RequestParam BigDecimal precinho){
        // buscar lista de produtos com preco menor que 10
        return repository.findByPrecoLessThan(precinho);
    }

    @PostMapping("/produtos/precoMaior")
    public List<Produto> findByPrecoMaior(@RequestParam BigDecimal precinho){
        return repository.findByPrecoGreaterThan(precinho);
    }

    @PostMapping("/produtos/precoEntre")
    public List<Produto> findByPrecoBetween(@RequestParam BigDecimal precinho, @RequestParam BigDecimal precao){
        return repository.findByPrecoBetween(precinho, precao);
    }

    @PutMapping("/produtos/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto produto)
// "UPDATE cliente SET ... WHERE ..."
            throws ResourceNotFoundException{
        return repository.findById(id).map(p -> {
            p.setNome(produto.getNome());
            p.setDescricao(produto.getDescricao());
            return repository.save(p);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não existe produto cadastrado com o id: " + id));
    }


    @PatchMapping("/produtos/alteraPreco/{id}") //quando a atualização é só em um atributo usa patch
    public Produto alterPrecoById(@PathVariable Long id, @RequestBody BigDecimal preco)
// "UPDATE cliente SET ... WHERE ..."
            throws ResourceNotFoundException{
        return repository.findById(id).map(p -> {
            p.setPreco(preco);
            return repository.save(p);
        }).orElseThrow(() ->
                new ResourceNotFoundException("Não existe produto cadastrado com o id: " + id));
    }

    @DeleteMapping("/produtos/{id}")
    public void delete(@PathVariable Long id){  //o @pathvaruable informa a variável q está no caminho e será "manipulada"
        //DELETE FROM produto WHERE id="..."
        repository.deleteById(id);
    }

    @Transactional
    @PostMapping("/produtos/delete-with-price/")
    public void deleteById(@RequestParam BigDecimal precoComparativo){
        repository.deleteProdutoByIdIsLessThan(precoComparativo);
    }

}
