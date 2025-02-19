package com.caetano.teste110225.repositorios;

import com.caetano.teste110225.entidades.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    static void delete(Long id) {
    }
}
