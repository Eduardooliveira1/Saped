package br.gov.mme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.mme.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
