package br.edu.infnet.hanielapi.service;

import br.edu.infnet.hanielapi.exception.AtivoInvalidoException;
import br.edu.infnet.hanielapi.exception.AtivoNaoEncontradoException;
import br.edu.infnet.hanielapi.model.Acao;
import br.edu.infnet.hanielapi.repository.AcaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class AcaoService implements CrudService<Acao, Long> {

    private final AcaoRepository acaoRepository;

    public AcaoService(AcaoRepository acaoRepository) {
        this.acaoRepository = acaoRepository;
    }

    private Acao encontrarPorIdOuLancarExcecao(Long id) {
        return acaoRepository.findById(id)
                .orElseThrow(() -> new AtivoNaoEncontradoException("Ação com ID " + id + " não encontrada."));
    }

    @Override
    public List<Acao> listarTodos() {
        return acaoRepository.findAll();
    }

    @Override
    public Optional<Acao> buscarPorId(Long id) {
        return acaoRepository.findById(id);
    }

    @Override
    public Acao salvar(Acao acao) {
        if (acao.getTicker() == null || acao.getTicker().isBlank()) {
            throw new AtivoInvalidoException("O ticker da ação não pode ser vazio.");
        }
        
        return acaoRepository.save(acao);
    }

    @Override
    public Acao alterar(Long id, Acao acaoParaAlterar) {
        encontrarPorIdOuLancarExcecao(id);
        
        acaoParaAlterar.setId(id);

        return acaoRepository.save(acaoParaAlterar);
    }

    @Override
    public void excluir(Long id) {
        encontrarPorIdOuLancarExcecao(id);
        acaoRepository.deleteById(id);
    }
    
    public Acao desdobrar(Long id, int fator) {
        Acao acao = encontrarPorIdOuLancarExcecao(id);
        if (fator <= 1) {
            throw new AtivoInvalidoException("O fator de split deve ser maior que 1.");
        }
        
        int novaQuantidade = acao.getQuantidade() * fator;
        BigDecimal novoPrecoMedio = acao.getPrecoMedio().divide(new BigDecimal(fator), 2, RoundingMode.HALF_UP);
        acao.setQuantidade(novaQuantidade);
        acao.setPrecoMedio(novoPrecoMedio);
        
        return acaoRepository.save(acao);
    }
}