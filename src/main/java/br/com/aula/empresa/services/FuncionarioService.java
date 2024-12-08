package br.com.aula.empresa.services;

import br.com.aula.empresa.classes.Funcionario;
import br.com.aula.empresa.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario criar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listar() {
        return funcionarioRepository.findAll();
    }

    public Funcionario listarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
    }

    @Transactional
    public Funcionario atualizar(Long id, Funcionario funcionario1) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        funcionario.setNome(funcionario1.getNome());
        funcionario.setNumeroRegistro(funcionario1.getNumeroRegistro());
        return funcionario;
    }

    @Transactional
    public void deletar(Long id) {
        funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        funcionarioRepository.deleteById(id);
    }
}