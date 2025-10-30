package br.edu.infnet.hanielapi.repository;

import br.edu.infnet.hanielapi.model.Acao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Long> {

}