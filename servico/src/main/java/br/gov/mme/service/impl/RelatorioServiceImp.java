package br.gov.mme.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.BoletoService;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.RelatorioService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.dto.ListaNomePessoaJuridicaDTO;

/**
 * Service Implementation for managing Relatorios.
 */
@Service
public class RelatorioServiceImp implements RelatorioService {
    
    private final BoletoService boletoService;

    private final PessoaJuridicaService pessoaJuridicaService;

    public RelatorioServiceImp(BoletoService boletoService, PessoaJuridicaService pessoaJuridicaService) {
        this.boletoService = boletoService;
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @Override
    public Page<BoletoRelatorioPagamentoDTO> listarPagamentos(Pageable pageable) {
        return boletoService.listarPagamentos(pageable);
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

    @Override
    public Set<ListaNomePessoaJuridicaDTO> getNomesPessoasJuridicas() {
        return pessoaJuridicaService.getNomesPessoasJuridicas();
    }

}
