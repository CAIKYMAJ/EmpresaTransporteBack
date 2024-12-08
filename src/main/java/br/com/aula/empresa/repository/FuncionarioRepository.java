package br.com.aula.empresa.repository;

import br.com.aula.empresa.classes.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
