package br.gov.mme.service;

import br.gov.mme.service.dto.CobrancaDTO;
import br.gov.mme.service.dto.DadosGerarBoletoDTO;
import br.gov.mme.service.dto.FiltroListagemCobrancaDTO;

import java.util.List;


public interface CobrancaService {
    List<CobrancaDTO> obterCobrancasDoAno(FiltroListagemCobrancaDTO filtro);

    CobrancaDTO gerarBoleto(DadosGerarBoletoDTO dadosDoBoleto);
}

