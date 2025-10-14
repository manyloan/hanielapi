package br.edu.infnet.hanielapi.controller;

import br.edu.infnet.hanielapi.model.Acao;
import br.edu.infnet.hanielapi.service.AcaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acoes")
public class AcaoController {

    private final AcaoService acaoService;

    public AcaoController(AcaoService acaoService) {
        this.acaoService = acaoService;
    }

    @GetMapping
    public ResponseEntity<List<Acao>> listarAcoes() {
        return ResponseEntity.ok(acaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acao> buscarAcaoPorId(@PathVariable Long id) {
        return acaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Acao> incluirAcao(@RequestBody Acao acao) {
        Acao novaAcao = acaoService.salvar(acao);
        return new ResponseEntity<>(novaAcao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Acao> alterarAcao(@PathVariable Long id, @RequestBody Acao acao) {
        Acao acaoAtualizada = acaoService.alterar(id, acao);
        return ResponseEntity.ok(acaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAcao(@PathVariable Long id) {
        acaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/desdobrar")
    public ResponseEntity<Acao> desdobrarAcao(@PathVariable Long id, @RequestParam int fator) {
        Acao acaoAtualizada = acaoService.desdobrar(id, fator);
        return ResponseEntity.ok(acaoAtualizada);
    }
}