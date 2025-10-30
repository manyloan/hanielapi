package br.edu.infnet.hanielapi.service;

import br.edu.infnet.hanielapi.config.IdGenerator;
import br.edu.infnet.hanielapi.exception.AtivoInvalidoException;
import br.edu.infnet.hanielapi.exception.AtivoNaoEncontradoException;
import br.edu.infnet.hanielapi.model.FundoImobiliario;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FundoImobiliarioService implements CrudService<FundoImobiliario, Long> {
    
    private final Map<Long, FundoImobiliario> fiis = new ConcurrentHashMap<>();
    private final IdGenerator idGenerator;

    public FundoImobiliarioService(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    private FundoImobiliario encontrarPorIdOuLancarExcecao(Long id) {
        return Optional.ofNullable(fiis.get(id))
                .orElseThrow(() -> new AtivoNaoEncontradoException("FII com ID " + id + " não encontrado."));
    }

    @Override
    public List<FundoImobiliario> listarTodos() {
        return new ArrayList<>(fiis.values());
    }

    @Override
    public Optional<FundoImobiliario> buscarPorId(Long id) {
        return Optional.ofNullable(fiis.get(id));
    }

    @Override
    public FundoImobiliario salvar(FundoImobiliario fii) {
        if (fii.getCodigo() == null || fii.getCodigo().isBlank()) {
            throw new AtivoInvalidoException("O código do FII não pode ser vazio.");
        }
        fii.setId(idGenerator.getNextId());
        fiis.put(fii.getId(), fii);
        return fii;
    }

    @Override
    public FundoImobiliario alterar(Long id, FundoImobiliario fiiParaAlterar) {
        FundoImobiliario fiiExistente = encontrarPorIdOuLancarExcecao(id);
        fiiParaAlterar.setId(id);
        fiis.put(id, fiiParaAlterar);
        return fiiParaAlterar;
    }

    @Override
    public void excluir(Long id) {
        encontrarPorIdOuLancarExcecao(id);
        fiis.remove(id);
    }
    
    public FundoImobiliario atualizarValorPatrimonial(Long id, BigDecimal novoValor) {
        FundoImobiliario fii = encontrarPorIdOuLancarExcecao(id);
        if (novoValor.compareTo(BigDecimal.ZERO) < 0) {
            throw new AtivoInvalidoException("O valor patrimonial não pode ser negativo.");
        }
        fii.setValorPatrimonialPorCota(novoValor);
        fiis.put(id, fii);
        return fii;
    }
}