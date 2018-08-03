package br.gov.mme.service;

import java.io.ByteArrayOutputStream;
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

    ByteArrayOutputStream getRelatorio(List<BoletoRelatorioPagamentoVO> voList);

}
