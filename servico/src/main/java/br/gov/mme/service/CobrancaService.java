package br.gov.mme.service;

import br.gov.mme.service.dto.cobrancaDTO;

import java.util.List;


public interface CobrancaService {
    List<cobrancaDTO> obterCobrancasDoAno(int ano, int idPj);
}

