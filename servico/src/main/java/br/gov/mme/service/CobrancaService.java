package br.gov.mme.service;

import br.gov.mme.service.dto.CobrancaDTO;

import java.util.List;


public interface CobrancaService {
    List<CobrancaDTO> obterCobrancasDoAno(int ano, Long idPj);
}

