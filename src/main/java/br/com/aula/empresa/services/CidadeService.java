package br.com.aula.empresa.services;

import br.com.aula.empresa.classes.Cidade;
import br.com.aula.empresa.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public Cidade criar(Cidade cidade) {
        cidade = cidadeRepository.save(cidade);
        cidade.setEstado(estadoService.listarPorId(cidade.getEstado().getId()));
        return cidadeRepository.findById(cidade.getId()).get();
    }

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    public Cidade listarPorId(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada."));
    }

    @Transactional
    public Cidade atualizar(Long id, Cidade cidade1) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada."));
        cidade.setNome(cidade1.getNome());
        cidade.setCodigo(cidade1.getCodigo());
        cidade.setEstado(estadoService.listarPorId(cidade1.getEstado().getId()));
        cidade.setPrecoUnitPeso(cidade1.getPrecoUnitPeso());
        cidade.setPrecoUnitValor(cidade1.getPrecoUnitValor());
        return cidade;
    }

    @Transactional
    public void deletar(Long id) {
        cidadeRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Cidade não encontrada."));
        cidadeRepository.deleteById(id);
    }
}