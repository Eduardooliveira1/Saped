package br.gov.mme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mme.domain.Pessoa;

@Repository
public interface PessoaRepository
		extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

	@Query(" from Pessoa p" +
	       " where p.id = :id")
	Pessoa getPessoa(@Param("id") Long id);

}
