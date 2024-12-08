package br.com.aula.empresa.repository;

import br.com.aula.empresa.classes.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> { }