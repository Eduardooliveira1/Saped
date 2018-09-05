package br.gov.mme.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaComboDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.service.dto.PessoaJuridicaNomeDTO;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long>, JpaSpecificationExecutor<PessoaJuridica> {

    @Query("select new br.gov.mme.service.dto.PessoaJuridicaListaDTO(p.id,p.cnpj, p.sigla, p.nomeFantasia, p.razaoSocial) from PessoaJuridica p where p.pessoa.status = 'S'")
    Page<PessoaJuridicaListaDTO> listarPessoasJuridicas(Pageable pageable);


    @Query("select new br.gov.mme.service.dto.PessoaJuridicaListaDTO(p.id,p.cnpj, p.sigla, p.nomeFantasia, p.razaoSocial) from PessoaJuridica p where p.pessoa.status = 'S' AND " +
            "(lower(p.nomeFantasia) like lower(:filtro) " +
            "OR lower(p.cnpj) like lower(:filtro)" +
            "OR lower(p.razaoSocial) like lower(:filtro)" +
            "OR lower(p.sigla) like lower(:filtro))")
    Page<PessoaJuridicaListaDTO
            > listarPessoasJuridicasComFiltro(@Param("filtro") String filtro, Pageable pageable);

    @Query("select new br.gov.mme.service.dto.PessoaJuridicaCadastroDTO(p.id,p.cnpj, p.sigla, p.nomeFantasia, p.razaoSocial) from PessoaJuridica p where p.pessoa.status = 'S' and p.cnpj = :cnpj")
    PessoaJuridicaCadastroDTO getCadastroDTOByCnpj(@Param("cnpj") String cnpj);

    @Query("from PessoaJuridica where cnpj = :cnpj")
    PessoaJuridica findByCnpj(@Param("cnpj") String cnpj);

    @Query("select new br.gov.mme.service.dto.PessoaJuridicaComboDTO(p.id, p.nomeFantasia) from PessoaJuridica p where p.pessoa.status = 'S' order by p.nomeFantasia" )
    List<PessoaJuridicaComboDTO> listarTodas();

    @Query("select new br.gov.mme.service.dto.PessoaJuridicaNomeDTO(p.id, p.cnpj, p.nomeFantasia) from PessoaJuridica p where p.pessoa.status = 'S'")
    List<PessoaJuridicaNomeDTO> getNomesByPJ();

}
