package br.edu.infnet.hanielapi.repository;

import br.edu.infnet.hanielapi.model.FundoImobiliario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FundoImobiliarioRepository extends JpaRepository<FundoImobiliario, Long> {

    List<FundoImobiliario> findBySegmentoIgnoreCaseAndValorPatrimonialPorCotaGreaterThan(String segmento, BigDecimal valorMinimo);
}