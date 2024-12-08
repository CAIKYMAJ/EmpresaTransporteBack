package br.com.aula.empresa.controller;

import br.com.aula.empresa.classes.Cliente;
import br.com.aula.empresa.classes.PessoaFisica;
import br.com.aula.empresa.classes.PessoaJuridica;
import br.com.aula.empresa.repository.PessoaFisicaRepository;
import br.com.aula.empresa.services.ClienteService;
import br.com.aula.empresa.services.PessoaFisicaService;
import br.com.aula.empresa.services.PessoaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @PostMapping("/criar/pessoa-fisica")
    public ResponseEntity<?> criar(@RequestBody PessoaFisica pessoaFisica) {
        try {
            return ResponseEntity.ok(pessoaFisicaService.criarPessoaFisica(pessoaFisica));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/criar/pessoa-juridica")
    public ResponseEntity<?> criar(@RequestBody PessoaJuridica pessoaJuridica) {
        try {
            return ResponseEntity.ok(pessoaJuridicaService.criarPessoaJuridica(pessoaJuridica));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping("/listar/pessoa-fisica")
    public List<PessoaFisica> listarPessoasFisicas() {
        return pessoaFisicaService.listar();
    }

    @GetMapping("/listar/pessoa-juridica")
    public List<PessoaJuridica> listarPessoasJuridicas() {
        return pessoaJuridicaService.listar();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarClientePorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(clienteService.listarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/editar/pessoa-fisica/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody PessoaFisica pessoaFisica) {
        try {
            return ResponseEntity.ok(pessoaFisicaService.atualizar(id, pessoaFisica));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/editar/pessoa-juridica/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody PessoaJuridica pessoaJuridica) {
        try {
            return ResponseEntity.ok(pessoaJuridicaService.atualizar(id, pessoaJuridica));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> excluirCliente(@PathVariable Long id) {
        try {
            clienteService.excluir(id);
            return ResponseEntity.ok("Cliente excluído com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}