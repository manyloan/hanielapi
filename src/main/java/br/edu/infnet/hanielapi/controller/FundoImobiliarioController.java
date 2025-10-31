package br.edu.infnet.hanielapi.controller;

import br.edu.infnet.hanielapi.controller.dto.UpdateValorPatrimonialRequest;
import br.edu.infnet.hanielapi.model.FundoImobiliario;
import br.edu.infnet.hanielapi.service.FundoImobiliarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fiis")
public class FundoImobiliarioController {

    private final FundoImobiliarioService fiiService;

    public FundoImobiliarioController(FundoImobiliarioService fiiService) {
        this.fiiService = fiiService;
    }

    @GetMapping
    public ResponseEntity<List<FundoImobiliario>> listarFiis() {
        return ResponseEntity.ok(fiiService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FundoImobiliario> buscarFiiPorId(@PathVariable Long id) {
        return fiiService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FundoImobiliario> incluirFii(@Valid @RequestBody FundoImobiliario fii) {
        FundoImobiliario novoFii = fiiService.salvar(fii);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoFii.getId())
                .toUri();
                
        return ResponseEntity.created(location).body(novoFii);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FundoImobiliario> alterarFii(@PathVariable Long id, @Valid @RequestBody FundoImobiliario fii) {
        FundoImobiliario fiiAtualizado = fiiService.alterar(id, fii);
        return ResponseEntity.ok(fiiAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFii(@PathVariable Long id) {
        fiiService.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/atualizar-valor-patrimonial")
    public ResponseEntity<FundoImobiliario> atualizarValorPatrimonial(
            @PathVariable Long id,
            @RequestBody UpdateValorPatrimonialRequest request) {
        FundoImobiliario fiiAtualizado = fiiService.atualizarValorPatrimonial(id, request.getNovoValor());
        return ResponseEntity.ok(fiiAtualizado);
    }

    @GetMapping("/busca/por-segmento-e-valor")
    public ResponseEntity<List<FundoImobiliario>> buscarFiisAvancado(
            @RequestParam String segmento,
            @RequestParam BigDecimal valorMin) {
        return ResponseEntity.ok(fiiService.buscarPorSegmentoEValorMaior(segmento, valorMin));
    }
}