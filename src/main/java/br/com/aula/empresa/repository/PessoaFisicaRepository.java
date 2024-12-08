package br.com.aula.empresa.repository;

import br.com.aula.empresa.classes.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> { }