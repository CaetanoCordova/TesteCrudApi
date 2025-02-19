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
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void excluir(Long id) {
        pessoaRepository.deleteById(id);
    }
}

//public Pessoa criarPessoa(Pessoa pessoa){
//    try{
//        Pessoas pessoasResult = pessoaRepository.save(pessoa);
//        return pessoasResult;
//    } catch (Exception e){
//        throw new RuntimeException(e.getMessage());
//    }
//    return null;
//}
