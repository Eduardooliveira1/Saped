package br.gov.mme.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mme.domain.Representante;
import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;
import br.gov.mme.service.dto.RepresentanteEMailECNPJDTO;
import br.gov.mme.service.dto.TelefoneListaRepresentanteDTO;


@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {

    @Query("SELECT new br.gov.mme.service.dto.PessoaRepresentanteListaDTO(r.id, r.nome, r.cargo, r.pessoa.email) "
        + " FROM Representante r WHERE r.pessoaJuridica.id = :idPj ")
    List<PessoaRepresentanteListaDTO> findRepresentantesByPessoaJuridica(@Param("idPj") Long idPj);

    @Query("SELECT new br.gov.mme.service.dto.TelefoneListaRepresentanteDTO(t.ddd, t.telefone, t.pessoaRepresentante.id) "
        + " FROM Telefone t WHERE t.pessoaRepresentante.pessoaJuridica.id = :idPj ")
    List<TelefoneListaRepresentanteDTO> findTelefonesByPessoaJuridica(@Param("idPj") Long idPj);

    @Query("SELECT new br.gov.mme.service.dto.RepresentanteEMailECNPJDTO(r.pessoa.email, r.pessoaJuridica.cnpj) " +
           "FROM Representante r " +
           "WHERE r.pessoa.email = :email AND r.pessoaJuridica.cnpj = :cnpj")
    RepresentanteEMailECNPJDTO findEmailECNPJ(@Param("email") String email, @Param("cnpj") String cnpj);

    @Query("select new br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO(r.pessoa.id,r.pessoaJuridica.id,r.pessoa.email, r.nome, r.pessoaJuridica.nomeFantasia) from Representante r")
    Page<ComunicacaoRepresentantelistaDTO> listarRepresentantes(Pageable pageable);

    @Query("select new br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO(r.pessoa.id,r.pessoaJuridica.id,r.pessoa.email, r.nome, r.pessoaJuridica.nomeFantasia) from Representante r where "
        + "(lower(r.pessoa.email) like lower(:filtro) " + "OR lower(r.nome) like lower(:filtro)"
        + "OR lower(r.pessoaJuridica.nomeFantasia) like lower(:filtro))")
    Page<ComunicacaoRepresentantelistaDTO> listarRepresentantesComFiltro(@Param("filtro") String filtro,
        Pageable pageable);
}
