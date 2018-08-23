package br.gov.mme.service;
import br.gov.mme.service.dto.CobrancaDTO;

public interface BoletoService {

    CobrancaDTO gerarBoleto(CobrancaDTO cobranca);

}
