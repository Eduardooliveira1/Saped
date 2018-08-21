package br.gov.mme.service;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;

import java.util.List;

public interface RepresentanteService {

    List<PessoaRepresentanteListaDTO> findRepresentantesByPessoaJuridica(Long idPj);
}
