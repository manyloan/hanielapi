package br.edu.infnet.hanielapi.repository;

import br.edu.infnet.hanielapi.model.Provento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProventoRepository extends JpaRepository<Provento, Long> {
    
    List<Provento> findByAcaoId(Long acaoId);
}