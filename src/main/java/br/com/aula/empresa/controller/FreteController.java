package br.com.aula.empresa.controller;

import br.com.aula.empresa.classes.ArrecadacaoDTO;
import br.com.aula.empresa.classes.Frete;
import br.com.aula.empresa.services.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/fretes")
public class FreteController {

    @Autowired
    private FreteService freteService;

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody Frete frete) {
        try {
            return ResponseEntity.ok(freteService.criar(frete));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public List<Frete> listar() {
        return freteService.listar();
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(freteService.listarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Frete frete) {
        try {
            return ResponseEntity.ok(freteService.atualizar(id, frete));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            freteService.deletar(id);
            return ResponseEntity.ok("Frete excluído com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/arrecadacoes/estado/{id}/ano/{ano}")
    public ResponseEntity<?> calcularArrecadacaoPorDestino(@PathVariable Long id, @PathVariable String ano) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(freteService.calcularArrecadacaoPorDestino(id, ano));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/media/estado/{id}")
    public ResponseEntity<?> calcularMediaFreteOrigemDestinoPorCidadeEstado(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(freteService.calcularMediaFreteOrigemDestinoPorCidadeEstado(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/pj-fretes/mes/{mes}/ano/{ano}")
    public ResponseEntity<?> calcularFretesAtendidosPorPJNoMesEAno(@PathVariable String mes, @PathVariable String ano) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(freteService.calcularFretesAtendidosPorPJNoMesEAno(mes, ano));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}