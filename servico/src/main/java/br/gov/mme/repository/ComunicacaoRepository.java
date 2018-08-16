package br.gov.mme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mme.domain.Notificacao;
import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;


@Repository
public interface ComunicacaoRepository extends JpaRepository<Notificacao, Long>, JpaSpecificationExecutor<Notificacao> {
    @Query("select new br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO(r.pessoa.id,r.pessoaJuridica.id,r.pessoa.email, r.nome, r.pessoaJuridica.nomeFantasia) from Representante r  ")
    Page<ComunicacaoRepresentantelistaDTO> listarRepresentantes(Pageable pageable);


    @Query("select new br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO(r.pessoa.id,r.pessoaJuridica.id,r.pessoa.email, r.nome, r.pessoaJuridica.nomeFantasia) from Representante r where " +
            "(lower(r.pessoa.email) like lower(:filtro) " +
            "OR lower(r.nome) like lower(:filtro)" +
            "OR lower(r.pessoaJuridica.nomeFantasia) like lower(:filtro))" )
    Page<ComunicacaoRepresentantelistaDTO> listarRepresentantesComFiltro(@Param("filtro") String filtro, Pageable pageable);

}
