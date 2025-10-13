package br.edu.infnet.hanielapi.controller;

import br.edu.infnet.hanielapi.model.Ativo; // MUDOU
import br.edu.infnet.hanielapi.service.AtivoService; // MUDOU
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ativos") // MUDOU
public class AtivoController {

    private final AtivoService ativoService; // MUDOU

    public AtivoController(AtivoService ativoService) { // MUDOU
        this.ativoService = ativoService;
    }

    @GetMapping
    public List<Ativo> listarTodos() { // MUDOU
        return ativoService.listarTodos();
    }
}