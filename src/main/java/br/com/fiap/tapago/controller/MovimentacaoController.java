package br.com.fiap.tapago.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.tapago.model.Movimentacao;
import br.com.fiap.tapago.repository.MovimentacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("movimentacao")
@Slf4j
public class MovimentacaoController {

    //Logger log = LoggerFactory.getLogger(getClass());//lombok substitui  usando-> @Slf4j
    
    
    @Autowired // Injeção de Dependência - Inversão de Controle
    MovimentacaoRepository repository;

    

    @GetMapping
    public List<Movimentacao> index() {
        return repository.findAll();
    }


   @PostMapping
   @ResponseStatus(CREATED)
     public Movimentacao create(@RequestBody @Valid Movimentacao movimentacao) {
        return repository.save(movimentacao);
     }

    

    @GetMapping("{id}")
    public ResponseEntity<Movimentacao> show(@PathVariable Long id) {
        log.info("buscando Movimentacao com id {}", id);
        
        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando categoria {}", id);
        verificarSeMovimentacaoExiste(id);
        repository.deleteById(id);
       

    }



    @PutMapping("{id}")
    public Movimentacao update(@PathVariable Long id, @RequestBody Movimentacao movimentacao){
        log.info("atualizar categoria {} para {}", id, movimentacao);
        verificarSeMovimentacaoExiste(id);
            movimentacao.setId(id);
            return repository.save(movimentacao);

            
         
        
    }


    private void verificarSeMovimentacaoExiste(Long id) {
        repository
        .findById(id)
        .orElseThrow(()-> new ResponseStatusException(
                                        NOT_FOUND, "Não existe movimentacao com o id informado."
                                    ));
    }


    
        
        
        
        
        
        
        
        
        
        
        
        
            // var categoriaEncontrada = repository.findById(id);

    //     if (categoriaEncontrada.isEmpty())
    //         return ResponseEntity.notFound().build();

    //     repository.delete(categoriaEncontrada.get());

    //     return ResponseEntity.noContent().build();
    // }







//     private Optional<Categoria> getCategoriaById(Long id) {
//         var categoriaEncontrada = repository
//                 .stream()
//                 .filter(c -> c.id().equals(id))
//                 .findFirst();
//         return categoriaEncontrada;
//     }

// }
    }



