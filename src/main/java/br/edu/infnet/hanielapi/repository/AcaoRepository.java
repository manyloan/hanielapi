package br.edu.infnet.hanielapi.repository;

import br.edu.infnet.hanielapi.model.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Long> {

    Optional<Acao> findByTicker(String ticker);

    List<Acao> findByEmpresaSetorIgnoreCase(String setor);
}