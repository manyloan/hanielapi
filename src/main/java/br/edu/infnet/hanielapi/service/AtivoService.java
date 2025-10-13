package br.edu.infnet.hanielapi.service;

import br.edu.infnet.hanielapi.model.Ativo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service // Anotação do spring para mostrar que essa classe é uma classe de servico. Se torna um Bean.
public class AtivoService implements CrudService<Ativo, Long> {
    // Criar o banco de dados em memória como mostrado na aula 2, aquele esquema que parece um sqlite/memcached.
    private final Map<Long, Ativo> ativos = new ConcurrentHashMap<>();

    private final AtomicLong idCounter = new AtomicLong(1);

    @Override // Esse carinha aqui vai la no CrudService e sobrescreve o método que está lá, deixando o codigo mais limpo.
    public List<Ativo> listarTodos() {
        return new ArrayList<>(ativos.values());
    }

    @Override
    public Optional<Ativo> buscarPorId(Long id) {
        return Optional.ofNullable(ativos.get(id));
    }

    @Override
    public Ativo salvar(Ativo Ativo) {
        if (Ativo.getId() == null) {
            long novoId = idCounter.getAndIncrement();
            Ativo.setId(novoId);
        }

        ativos.put(Ativo.getId(), Ativo);
        return Ativo;
    }

    @Override
    public void excluir(Long id) {
        ativos.remove(id);
    }

}
