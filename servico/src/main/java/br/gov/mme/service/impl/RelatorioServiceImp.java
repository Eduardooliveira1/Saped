package br.gov.mme.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.BoletoService;
import br.gov.mme.service.RelatorioService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

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
    public List<BoletoRelatorioPagamentoDTO> converterFiltroToVO(BoletoRelatorioPagamentoFiltroDTO filtro) {
        return boletoService.converterFiltroToVO(filtro);
    }

    @Override
    public byte[] getRelatorio(List<BoletoRelatorioPagamentoDTO> voList)
            throws CheckedInvalidArgumentException, RelatorioException, LeituraBufferException {
        return boletoService.getRelatorio(voList);
    }

}
