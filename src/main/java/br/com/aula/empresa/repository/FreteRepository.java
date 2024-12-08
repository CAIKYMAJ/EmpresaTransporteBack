package br.com.aula.empresa.repository;

import br.com.aula.empresa.classes.ArrecadacaoDTO;
import br.com.aula.empresa.classes.Frete;
import br.com.aula.empresa.classes.FretePJDTO;
import br.com.aula.empresa.classes.MediaFretesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreteRepository extends JpaRepository<Frete, Long> {

    @Query("SELECT new br.com.aula.empresa.classes.ArrecadacaoDTO(" +
            "c.nome, e.nome, count(*), " +
            "ROUND(sum(CASE " +
            "WHEN f.peso IS NOT NULL THEN f.peso * c.precoUnitPeso " +
            "ELSE f.valor * c.precoUnitValor " +
            "END + f.pedagio + " +
            "CASE " +
            "WHEN c.estado.id = :estadoId THEN f.icms * e.icmsLocal " +
            "ELSE f.icms * e.icmsOutroUf " +
            "END), 2)) " +
            "FROM Frete f " +
            "JOIN f.destino c " +
            "JOIN c.estado e " +
            "WHERE c.estado.id = :estadoId AND " +
            "SUBSTRING(f.dataFrete, 7, 4) = :ano " +
            "GROUP BY c.nome, e.nome")
    List<ArrecadacaoDTO> findAllArrecadacaoByDestino(@Param("estadoId") Long estadoId, @Param("ano") String ano);

    @Query("SELECT new br.com.aula.empresa.classes.MediaFretesDTO(" +
            "e.nome, c.nome, " +
            "COALESCE(AVG(origem_fretes.qtd_origem), 0), " +
            "COALESCE(AVG(destino_fretes.qtd_destino), 0)) " +
            "FROM Cidade c " +
            "JOIN c.estado e " +
            "LEFT JOIN (SELECT f.origem.id AS origem_id, COUNT(f) AS qtd_origem FROM Frete f GROUP BY f.origem.id) origem_fretes " +
            "ON origem_fretes.origem_id = c.id " +
            "LEFT JOIN (SELECT f.destino.id AS destino_id, COUNT(f) AS qtd_destino FROM Frete f GROUP BY f.destino.id) destino_fretes " +
            "ON destino_fretes.destino_id = c.id " +
            "WHERE e.id = :estadoId " +
            "GROUP BY e.nome, c.nome")
    List<MediaFretesDTO> findFreteMediaPorEstado(@Param("estadoId") Long estadoId);

    @Query("SELECT new br.com.aula.empresa.classes.FretePJDTO(" +
            "fun.nome, " +
            "pj.razaoSocial, " +
            "pf.nome, " +
            "f.dataFrete, " +
            "f.valor )" +
            "FROM Frete f " +
            "JOIN Funcionario fun ON fun.id = f.funcionario.id " +
            "JOIN Cliente c ON c.id = f.remetente.id OR c.id = f.destinatario.id " +
            "JOIN PessoaJuridica pj ON pj.id = c.id " +
            "JOIN PessoaFisica pf ON pf.id = pj.representante.id " +
            "WHERE SUBSTRING(f.dataFrete, 4, 2) LIKE :mes " +
            "AND SUBSTRING(f.dataFrete, 7, 4) LIKE :ano")
    List<FretePJDTO> findFretesAtendidosPorPJNoMesEAno(@Param("mes") String mes, @Param("ano") String ano);
}