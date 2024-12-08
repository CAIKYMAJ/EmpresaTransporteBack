package br.com.aula.empresa.services;

import br.com.aula.empresa.classes.Cliente;
import br.com.aula.empresa.classes.PessoaFisica;
import br.com.aula.empresa.classes.PessoaJuridica;
import br.com.aula.empresa.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaJuridicaService {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public PessoaJuridica criarPessoaJuridica(PessoaJuridica pessoaJuridica) {
        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    public List<PessoaJuridica> listar() {
        return pessoaJuridicaRepository.findAll();
    }

    @Transactional
    public Cliente atualizar(Long id, PessoaJuridica pessoaJuridica) {
        PessoaJuridica pessoaJuridica1 = pessoaJuridicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        pessoaJuridica1.setCodigoCli(pessoaJuridica.getEndereco());
        pessoaJuridica1.setDataInscrisao(pessoaJuridica.getDataInscrisao());
        pessoaJuridica1.setEndereco(pessoaJuridica.getEndereco());
        pessoaJuridica1.setTelefone(pessoaJuridica.getTelefone());
        pessoaJuridica1.setRazaoSocial(pessoaJuridica.getRazaoSocial());
        pessoaJuridica1.setCnpj(pessoaJuridica.getCnpj());
        pessoaJuridica1.setInscricaoEstadual(pessoaJuridica.getInscricaoEstadual());
        pessoaJuridica1.setRepresentante(pessoaJuridica.getRepresentante());
        return pessoaJuridica1;
    }
}