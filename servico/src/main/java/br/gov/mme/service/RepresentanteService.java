package br.gov.mme.service;
import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepresentanteService {

    List<PessoaRepresentanteListaDTO> findRepresentantesByPessoaJuridica(Long idPj);
    
    /**
     * Lita representantes
     * @param pageable
     * @return a lista de representantes
     */
    Page<ComunicacaoRepresentantelistaDTO> listarRepresentantes(String filtro,Pageable pageable);
}
