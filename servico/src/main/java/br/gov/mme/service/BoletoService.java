package br.gov.mme.service;

import java.util.List;

import br.gov.mee.vo.BoletoRelatorioPagamentoVO;
import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

/**
 * Service Interface for managing Boleto.
 * 
 * @see BoletoServiceImpl
 */
public interface BoletoService {

    List<BoletoRelatorioPagamentoVO> converterFiltroToVO(BoletoRelatorioPagamentoFiltroDTO filtro);

    byte[] getRelatorio(List<BoletoRelatorioPagamentoVO> voList)
            throws CheckedInvalidArgumentException, RelatorioException, LeituraBufferException;

}
