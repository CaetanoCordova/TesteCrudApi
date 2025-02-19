package com.caetano.teste110225.controllers;

import com.caetano.teste110225.entidades.Pessoa;
import com.caetano.teste110225.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarTodos() {
        return pessoaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
        return pessoa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
        return pessoaService.buscarPorId(id)
                .map(pessoa -> {
                    pessoa.setNome(novaPessoa.getNome());
                    pessoa.setSobrenome(novaPessoa.getSobrenome());
                    pessoa.setIdade(novaPessoa.getIdade());
                    pessoa.setAltura(novaPessoa.getAltura());
                    pessoaService.salvar(pessoa);
                    return ResponseEntity.ok(pessoa);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (pessoaService.buscarPorId(id).isPresent()) {
            pessoaService.excluir(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
