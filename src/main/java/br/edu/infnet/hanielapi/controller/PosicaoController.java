package br.edu.infnet.hanielapi.controller;

import br.edu.infnet.hanielapi.model.Posicao;
import br.edu.infnet.hanielapi.service.PosicaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posicoes")
public class PosicaoController {
    private final PosicaoService posicaoService;

    public PosicaoController(PosicaoService posicaoService) {
        this.posicaoService = posicaoService;
    }

    @GetMapping
    public List<Posicao> listarTodas() {
        return posicaoService.listarTodos();
    }
}
