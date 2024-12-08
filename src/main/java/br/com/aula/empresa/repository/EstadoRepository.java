package br.com.aula.empresa.repository;

import br.com.aula.empresa.classes.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
