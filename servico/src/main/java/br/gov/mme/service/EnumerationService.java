package br.gov.mme.service;

import java.util.List;

import br.gov.mme.service.dto.EnumerationDTO;
import br.gov.mme.service.dto.PessoaJuridicaNomeDTO;

public interface EnumerationService {

    List<EnumerationDTO> getAllTipoEndereco();

    List<EnumerationDTO> getAllStatusBoleto();

    List<EnumerationDTO> getAllNomesPJ(List<PessoaJuridicaNomeDTO> nomesPessoasJuridicas);

}
