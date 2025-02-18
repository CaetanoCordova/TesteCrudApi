package com.caetano.teste110225.controllers;

import com.caetano.teste110225.entidades.Pessoa;
import com.caetano.teste110225.repositorios.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
