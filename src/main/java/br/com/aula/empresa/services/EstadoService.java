package br.com.aula.empresa.services;

import br.com.aula.empresa.classes.Estado;
import br.com.aula.empresa.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado criar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    public Estado listarPorId(Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado."));
    }

    @Transactional
    public Estado atualizar(Long id, Estado estado1) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado."));
        estado.setNome(estado1.getNome());
        estado.setIcmsLocal(estado1.getIcmsLocal());
        estado.setIcmsOutroUf(estado1.getIcmsOutroUf());
        estado.setUf(estado1.getUf());
        return estado;
    }

    @Transactional
    public void deletar(Long id) {
        estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado."));
        estadoRepository.deleteById(id);
    }
}
