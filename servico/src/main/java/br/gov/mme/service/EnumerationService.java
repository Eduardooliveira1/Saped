package br.gov.mme.service;

import java.util.List;

import br.gov.mme.service.dto.EnumerationDTO;

public interface EnumerationService {

    List<EnumerationDTO> getAllTipoEndereco();

    List<EnumerationDTO> getAllNomesPjs();

    List<EnumerationDTO> getAllStatusBoleto();

}
