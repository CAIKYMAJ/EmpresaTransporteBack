package br.com.aula.empresa.services;

import br.com.aula.empresa.classes.Cliente;
import br.com.aula.empresa.classes.PessoaFisica;
import br.com.aula.empresa.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisica criarPessoaFisica(PessoaFisica pessoaFisica) {
        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public List<PessoaFisica> listar() {
        return pessoaFisicaRepository.findAll();
    }

    @Transactional
    public Cliente atualizar(Long id, PessoaFisica pessoaFisica) {
        PessoaFisica pessoaFisica1 = pessoaFisicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        pessoaFisica1.setCodigoCli(pessoaFisica.getCodigoCli());
        pessoaFisica1.setDataInscrisao(pessoaFisica.getDataInscrisao());
        pessoaFisica1.setEndereco(pessoaFisica.getEndereco());
        pessoaFisica1.setTelefone(pessoaFisica.getTelefone());
        pessoaFisica1.setCpf(pessoaFisica.getCpf());
        pessoaFisica1.setNome(pessoaFisica.getNome());
        return pessoaFisica1;
    }
}