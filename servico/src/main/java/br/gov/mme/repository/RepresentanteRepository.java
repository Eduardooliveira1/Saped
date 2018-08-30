package br.gov.mme.repository;

import br.gov.mme.domain.Representante;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;
import br.gov.mme.service.dto.RepresentanteEMailECNPJDTO;
import br.gov.mme.service.dto.TelefoneListaRepresentanteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {

    @Query("SELECT new br.gov.mme.service.dto.PessoaRepresentanteListaDTO(r.id, r.nome, r.cargo, r.pessoa.email) " +
            " FROM Representante r WHERE r.pessoaJuridica.id = :idPj ")
    List<PessoaRepresentanteListaDTO> findRepresentantesByPessoaJuridica(@Param("idPj") Long idPj);


    @Query("SELECT new br.gov.mme.service.dto.TelefoneListaRepresentanteDTO(t.ddd, t.telefone, t.pessoaRepresentante.id) " +
            " FROM Telefone t WHERE t.pessoaRepresentante.pessoaJuridica.id = :idPj ")
    List<TelefoneListaRepresentanteDTO> findTelefonesByPessoaJuridica(@Param("idPj") Long idPj);
    
    @Query("SELECT new br.gov.mme.service.dto.RepresentanteEMailECNPJDTO(r.Pessoa.email, r.PessoaJuridica.cnpj) " +
           "FROM Representante r " +
           "WHERE r.Pessoa.email = :email AND r.PessoaJuridica.cnpj = :cnpj")
    RepresentanteEMailECNPJDTO findEmailECNPJ(@Param("email") String email, @Param("cnpj") String cnpj);
    
}
