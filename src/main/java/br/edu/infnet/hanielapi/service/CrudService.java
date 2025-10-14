package br.edu.infnet.hanielapi.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    T salvar(T entity);

    T alterar(ID id, T entity);

    void excluir(ID id);

    Optional<T> buscarPorId(ID id);

    List<T> listarTodos();    
}
