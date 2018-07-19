package br.gov.mme.repository;

import br.gov.mme.domain.PessoaJuridica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long>, JpaSpecificationExecutor<PessoaJuridica> {

    @Query("select P from PessoaJuridica P where P.pessoa.status = 'S'")
    Page<PessoaJuridica> listarPessoasJuridicas(Pageable pageable);


    @Query("select P from PessoaJuridica P where P.pessoa.status = 'S' AND " +
            "(lower(P.nomeFantasia) like lower(:filtro) " +
            "OR lower(P.cnpj) like lower(:filtro)" +
            "OR lower(P.razaoSocial) like lower(:filtro)" +
            "OR lower(P.sigla) like lower(:filtro))")
    Page<PessoaJuridica> listarPessoasJuridicasComFiltro(@Param("filtro") String filtro, Pageable pageable);


}
