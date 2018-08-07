package br.gov.mme.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.gov.mme.service.BoletoService;
import br.gov.mme.service.RelatorioService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;

/**
 * Service Implementation for managing Relatorios.
 */
@Service
public class RelatorioServiceImp implements RelatorioService {
    
    private final BoletoService boletoService;

    public RelatorioServiceImp(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @Override
    public Page<BoletoRelatorioPagamentoDTO> listarPagamentos(Pageable pageable) {
        return boletoService.listarPagamentos(pageable);
    }

}
