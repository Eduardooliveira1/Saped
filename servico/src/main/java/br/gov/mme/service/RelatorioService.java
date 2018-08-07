package br.gov.mme.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.impl.RelatorioServiceImp;

/**
 * Service Interface for managing Relatorio.
 * 
 * @see RelatorioServiceImp
 */
public interface RelatorioService {

    Page<BoletoRelatorioPagamentoDTO> listarPagamentos(Pageable pageable);

}
