package br.com.aula.empresa.repository;

import br.com.aula.empresa.classes.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> { }