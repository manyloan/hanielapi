package br.edu.infnet.hanielapi.service;

import br.edu.infnet.hanielapi.config.IdGenerator;
import br.edu.infnet.hanielapi.exception.AtivoInvalidoException;
import br.edu.infnet.hanielapi.exception.AtivoNaoEncontradoException;
import br.edu.infnet.hanielapi.model.Acao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AcaoService implements CrudService<Acao, Long> {

    private final Map<Long, Acao> acoes = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator;

    public AcaoService(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    private Acao encontrarPorIdOuLancarExcecao(Long id) {
        return Optional.ofNullable(acoes.get(id))
                .orElseThrow(() -> new AtivoNaoEncontradoException("Ação com ID " + id + " não encontrada."));
    }

    @Override
    public List<Acao> listarTodos() {
        return new ArrayList<>(acoes.values());
    }

    @Override
    public Optional<Acao> buscarPorId(Long id) {
        return Optional.ofNullable(acoes.get(id));
    }

    @Override
    public Acao salvar(Acao acao) {
        if (acao.getTicker() == null || acao.getTicker().isBlank()) {
            throw new AtivoInvalidoException("O ticker da ação não pode ser vazio.");
        }
        acao.setId(idGenerator.getNextId());
        acoes.put(acao.getId(), acao);
        return acao;
    }

    @Override
    public Acao alterar(Long id, Acao acaoParaAlterar) {
        Acao acaoExistente = encontrarPorIdOuLancarExcecao(id);
        acaoParaAlterar.setId(id); 
        acoes.put(id, acaoParaAlterar);
        return acaoParaAlterar;
    }

    @Override
    public void excluir(Long id) {
        encontrarPorIdOuLancarExcecao(id);
        acoes.remove(id);
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
        acoes.put(id, acao);
        return acao;
    }
}