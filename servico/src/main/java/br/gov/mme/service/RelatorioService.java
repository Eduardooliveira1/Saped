package br.gov.mme.service;

import java.util.List;

import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

/**
 * Service Interface for managing Relatorio.
 * 
 * @see RelatorioServiceImp
 */
public interface RelatorioService {

    List<BoletoRelatorioPagamentoDTO> converterFiltroToVO(BoletoRelatorioPagamentoFiltroDTO filtro);

    byte[] getRelatorio(List<BoletoRelatorioPagamentoDTO> voList)
            throws CheckedInvalidArgumentException, RelatorioException, LeituraBufferException;

}