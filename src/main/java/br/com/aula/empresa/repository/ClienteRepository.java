package br.com.aula.empresa.repository;

import br.com.aula.empresa.classes.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM PessoaFisica c WHERE c.cpf = :cpf")
    boolean existsByCpf(@Param("cpf") String cpf);
}