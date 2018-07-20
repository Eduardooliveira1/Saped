package br.gov.mme.repository;

import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long>, JpaSpecificationExecutor<PessoaJuridica> {

    @Query("select new br.gov.mme.service.dto.PessoaJuridicaListaDTO(p.cnpj, p.sigla, p.nomeFantasia, p.razaoSocial) from PessoaJuridica p where p.pessoa.status = 'S'")
    Page<PessoaJuridicaListaDTO> listarPessoasJuridicas(Pageable pageable);


    @Query("select new br.gov.mme.service.dto.PessoaJuridicaListaDTO(p.cnpj, p.sigla, p.nomeFantasia, p.razaoSocial) from PessoaJuridica p where p.pessoa.status = 'S' AND " +
            "(lower(p.nomeFantasia) like lower(:filtro) " +
            "OR lower(p.cnpj) like lower(:filtro)" +
            "OR lower(p.razaoSocial) like lower(:filtro)" +
            "OR lower(p.sigla) like lower(:filtro))")
    Page<PessoaJuridicaListaDTO
            > listarPessoasJuridicasComFiltro(@Param("filtro") String filtro, Pageable pageable);


}