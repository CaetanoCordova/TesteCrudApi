package com.caetano.teste110225.services;

import com.caetano.teste110225.entidades.Pessoa;
import com.caetano.teste110225.repositorios.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private static PessoaRepository pessoaRepository;

    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public static Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public static Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public static void delete(Long id) {
        pessoaRepository.deleteById(id);
    }
}
