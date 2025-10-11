package br.edu.infnet.hanielapi.service;

import br.edu.infnet.hanielapi.model.Posicao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service // Anotação do spring para mostrar que essa classe é uma classe de servico. Se torna um Bean.
public class PosicaoService implements CrudService<Posicao, Long> {
    // Criar o banco de dados em memória como mostrado na aula 2, aquele esquema que parece um sqlite/memcached.
    private final Map<Long, Posicao> posicoes = new ConcurrentHashMap<>();

    private final AtomicLong idCounter = new AtomicLong(1);

    @Override // Esse carinha aqui vai la no CrudService e sobrescreve o método que está lá, deixando o codigo mais limpo.
    public List<Posicao> listarTodos() {
        return new ArrayList<>(posicoes.values());
    }

    @Override
    public Optional<Posicao> buscarPorId(Long id) {
        return Optional.ofNullable(posicoes.get(id));
    }

    @Override
    public Posicao salvar(Posicao posicao) {
        if (posicao.getId() == null) {
            long novoId = idCounter.getAndIncrement();
            posicao.setId(novoId);
        }

        posicoes.put(posicao.getId(), posicao);
        return posicao;
    }

    @Override
    public void excluir(Long id) {
        posicoes.remove(id);
    }

}
