package br.com.aula.empresa.controller;

import br.com.aula.empresa.classes.Funcionario;
import br.com.aula.empresa.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody Funcionario funcionario) {
        try {
            return ResponseEntity.ok(funcionarioService.criar(funcionario));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<Funcionario> listar() {
        return funcionarioService.listar();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Funcionario> listarPorId(@PathVariable Long id) {
        try {
            Funcionario funcionario = funcionarioService.listarPorId(id);
            return ResponseEntity.ok(funcionario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        try {
            return ResponseEntity.ok(funcionarioService.atualizar(id, funcionario));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            funcionarioService.deletar(id);
            return ResponseEntity.ok("Funcionário excluído com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}