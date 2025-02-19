package com.caetano.teste110225.controllers;

import com.caetano.teste110225.entidades.Pessoa;
import com.caetano.teste110225.repositorios.PessoaRepository;
import com.caetano.teste110225.services.PessoaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pessoa")
@CrossOrigin
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping("/criar")
    public ResponseEntity<Pessoa> criarPessoa(
            @RequestBody Pessoa pessoa
    ){
        Pessoa retorno = pessoaRepository.save(pessoa);

        if(retorno != null){
            return ResponseEntity.ok().body(retorno);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = PessoaService.findById(id);
        return pessoa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> save(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
        return PessoaService.findById(id)
                .map(pessoa -> {
                    pessoa.setNome(novaPessoa.getNome());
                    pessoa.setSobrenome(novaPessoa.getSobrenome());
                    pessoa.setIdade(novaPessoa.getIdade());
                    pessoa.setAltura(novaPessoa.getAltura());
                    PessoaService.save(pessoa);
                    return ResponseEntity.ok(pessoa);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (PessoaService.findById(id).isPresent()) {
            PessoaService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
