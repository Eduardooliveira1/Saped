package br.gov.mme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.gov.mme.domain.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long>, JpaSpecificationExecutor<Boleto> {

}
