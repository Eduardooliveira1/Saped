package br.gov.mme.service;
import java.util.List;

import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;
import br.gov.mme.service.dto.RepresentanteEMailECNPJDTO;

public interface RepresentanteService {

    List<PessoaRepresentanteListaDTO> findRepresentantesByPessoaJuridica(Long idPj);

    Boolean verificaCNPJEEmailValidos(RepresentanteEMailECNPJDTO representanteEMailECNPJDTO);
}
