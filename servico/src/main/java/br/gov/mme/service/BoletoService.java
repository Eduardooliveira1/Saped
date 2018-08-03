package br.gov.mme.service;

import java.util.List;

import br.gov.mee.vo.BoletoRelatorioPagamentoVO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

/**
 * Service Interface for managing Boleto.
 * 
 * @see BoletoServiceImpl
 */
public interface BoletoService {

    List<BoletoRelatorioPagamentoVO> converterFiltroToVO(BoletoRelatorioPagamentoFiltroDTO filtro);

    BoletoRelatorioPagamentoVO getRelatorio(List<BoletoRelatorioPagamentoVO> voList);

}
