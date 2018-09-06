package br.gov.mme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.gov.mme.domain.StatusBoleto;

@Repository
public interface StatusBoletoRepository
        extends JpaRepository<StatusBoleto, Long>, JpaSpecificationExecutor<StatusBoleto> {

}
