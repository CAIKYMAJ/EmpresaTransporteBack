package br.com.aula.empresa.services;

import br.com.aula.empresa.classes.Cliente;
import br.com.aula.empresa.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente listarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }

    @Transactional
    public void excluir(Long id) {
        clienteRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        clienteRepository.deleteById(id);
    }
}