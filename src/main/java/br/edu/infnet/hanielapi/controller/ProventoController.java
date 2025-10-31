package br.edu.infnet.hanielapi.controller;

import br.edu.infnet.hanielapi.model.Provento;
import br.edu.infnet.hanielapi.service.ProventoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping
public class ProventoController {

    private final ProventoService proventoService;

    public ProventoController(ProventoService proventoService) {
        this.proventoService = proventoService;
    }

    @PostMapping("/acoes/{acaoId}/proventos")
    public ResponseEntity<Provento> incluirProvento(@PathVariable Long acaoId, @Valid @RequestBody Provento provento) {
        Provento novoProvento = proventoService.salvar(provento, acaoId);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/proventos/{id}")
                .buildAndExpand(novoProvento.getId())
                .toUri();
                
        return ResponseEntity.created(location).body(novoProvento);
    }

    @GetMapping("/acoes/{acaoId}/proventos")
    public ResponseEntity<List<Provento>> listarProventosPorAcao(@PathVariable Long acaoId) {
        return ResponseEntity.ok(proventoService.listarPorAcaoId(acaoId));
    }

    @GetMapping("/proventos")
    public ResponseEntity<List<Provento>> listarTodosProventos() {
        return ResponseEntity.ok(proventoService.listarTodos());
    }

    @GetMapping("/proventos/{id}")
    public ResponseEntity<Provento> buscarProventoPorId(@PathVariable Long id) {
        return proventoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/proventos/{id}")
    public ResponseEntity<Provento> alterarProvento(@PathVariable Long id, @Valid @RequestBody Provento provento) {
        Provento proventoAtualizado = proventoService.alterar(id, provento);
        return ResponseEntity.ok(proventoAtualizado);
    }

    @DeleteMapping("/proventos/{id}")
    public ResponseEntity<Void> excluirProvento(@PathVariable Long id) {
        proventoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}