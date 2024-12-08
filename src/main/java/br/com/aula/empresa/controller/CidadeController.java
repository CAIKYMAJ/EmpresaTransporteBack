package br.com.aula.empresa.controller;

import br.com.aula.empresa.classes.Cidade;
import br.com.aula.empresa.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody Cidade cidade) {
        try {
            return ResponseEntity.ok(cidadeService.criar(cidade));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<Cidade> listar() {
        return cidadeService.listar();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Cidade> buscarPorId(@PathVariable Long id) {
        try {
            Cidade cidade = cidadeService.listarPorId(id);
            return ResponseEntity.ok(cidade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        try {
            return ResponseEntity.ok(cidadeService.atualizar(id, cidade));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        try {
            cidadeService.deletar(id);
            return ResponseEntity.ok("Cidade excluído com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}