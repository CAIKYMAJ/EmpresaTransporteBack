package br.com.aula.empresa.controller;

import br.com.aula.empresa.classes.Estado;
import br.com.aula.empresa.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody Estado estado) {
        try {
            return ResponseEntity.ok(estadoService.criar(estado));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<Estado> listar() {
        return estadoService.listar();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Estado> listarPorId(@PathVariable Long id) {
        try {
            Estado estado = estadoService.listarPorId(id);
            return ResponseEntity.ok(estado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        try {
            estadoService.atualizar(id, estado);
            return ResponseEntity.ok(estadoService.atualizar(id, estado));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            estadoService.deletar(id);
            return ResponseEntity.ok("Estado excluído com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}