package br.edu.infnet.hanielapi.service;

import br.edu.infnet.hanielapi.exception.AtivoInvalidoException;
import br.edu.infnet.hanielapi.exception.AtivoNaoEncontradoException;
import br.edu.infnet.hanielapi.model.FundoImobiliario;
import br.edu.infnet.hanielapi.repository.FundoImobiliarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class FundoImobiliarioService implements CrudService<FundoImobiliario, Long> {
    
    private final FundoImobiliarioRepository fiiRepository;

    public FundoImobiliarioService(FundoImobiliarioRepository fiiRepository) {
        this.fiiRepository = fiiRepository;
    }

    private FundoImobiliario encontrarPorIdOuLancarExcecao(Long id) {
        return fiiRepository.findById(id)
                .orElseThrow(() -> new AtivoNaoEncontradoException("FII com ID " + id + " não encontrado."));
    }

    @Override
    public List<FundoImobiliario> listarTodos() {
        return fiiRepository.findAll();
    }

    @Override
    public Optional<FundoImobiliario> buscarPorId(Long id) {
        return fiiRepository.findById(id);
    }

    @Override
    public FundoImobiliario salvar(FundoImobiliario fii) {
        if (fii.getCodigo() == null || fii.getCodigo().isBlank()) {
            throw new AtivoInvalidoException("O código do FII não pode ser vazio.");
        }
        
        return fiiRepository.save(fii);
    }

    @Override
    public FundoImobiliario alterar(Long id, FundoImobiliario fiiParaAlterar) {
        encontrarPorIdOuLancarExcecao(id);
        fiiParaAlterar.setId(id);
        return fiiRepository.save(fiiParaAlterar);
    }

    @Override
    public void excluir(Long id) {
        encontrarPorIdOuLancarExcecao(id);
        fiiRepository.deleteById(id);
    }
    
    public FundoImobiliario atualizarValorPatrimonial(Long id, BigDecimal novoValor) {
        FundoImobiliario fii = encontrarPorIdOuLancarExcecao(id);
        if (novoValor.compareTo(BigDecimal.ZERO) < 0) {
            throw new AtivoInvalidoException("O valor patrimonial não pode ser negativo.");
        }
        
        fii.setValorPatrimonialPorCota(novoValor);
        
        return fiiRepository.save(fii);
    }
}