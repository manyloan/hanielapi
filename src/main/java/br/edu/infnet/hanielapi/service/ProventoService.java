package br.edu.infnet.hanielapi.service;

import br.edu.infnet.hanielapi.exception.AtivoNaoEncontradoException;
import br.edu.infnet.hanielapi.model.Acao;
import br.edu.infnet.hanielapi.model.Provento;
import br.edu.infnet.hanielapi.repository.AcaoRepository;
import br.edu.infnet.hanielapi.repository.ProventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProventoService {

    private final ProventoRepository proventoRepository;
    private final AcaoRepository acaoRepository;

    public ProventoService(ProventoRepository proventoRepository, AcaoRepository acaoRepository) {
        this.proventoRepository = proventoRepository;
        this.acaoRepository = acaoRepository;
    }

    public Provento salvar(Provento provento, Long acaoId) {
        Acao acao = acaoRepository.findById(acaoId)
                .orElseThrow(() -> new AtivoNaoEncontradoException("Ação com ID " + acaoId + " não encontrada para associar o provento."));
        
        provento.setAcao(acao);
        return proventoRepository.save(provento);
    }

    public List<Provento> listarTodos() {
        return proventoRepository.findAll();
    }
    
    public List<Provento> listarPorAcaoId(Long acaoId) {
        if (!acaoRepository.existsById(acaoId)) {
            throw new AtivoNaoEncontradoException("Ação com ID " + acaoId + " não encontrada.");
        }
        return proventoRepository.findByAcaoId(acaoId);
    }

    public Optional<Provento> buscarPorId(Long id) {
        return proventoRepository.findById(id);
    }
    
    public Provento alterar(Long id, Provento proventoParaAlterar) {
        Provento proventoExistente = proventoRepository.findById(id)
                .orElseThrow(() -> new AtivoNaoEncontradoException("Provento com ID " + id + " não encontrado."));

        proventoExistente.setTipo(proventoParaAlterar.getTipo());
        proventoExistente.setDataPagamento(proventoParaAlterar.getDataPagamento());
        proventoExistente.setValorPorAcao(proventoParaAlterar.getValorPorAcao());

        return proventoRepository.save(proventoExistente);
    }

    public void excluir(Long id) {
        if (!proventoRepository.existsById(id)) {
            throw new AtivoNaoEncontradoException("Provento com ID " + id + " não encontrado para exclusão.");
        }
        proventoRepository.deleteById(id);
    }
}