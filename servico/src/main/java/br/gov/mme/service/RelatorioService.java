package br.gov.mme.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.dto.ListaNomePessoaJuridicaDTO;

/**
 * Service Interface for managing Relatorio.
 * 
 * @see RelatorioServiceImp
 */
public interface RelatorioService {

    Page<BoletoRelatorioPagamentoDTO> listarPagamentos(Pageable pageable);

    List<BoletoRelatorioPagamentoDTO> converterFiltroToVO(BoletoRelatorioPagamentoFiltroDTO filtro);

    byte[] getRelatorio(List<BoletoRelatorioPagamentoDTO> voList)
            throws CheckedInvalidArgumentException, RelatorioException, LeituraBufferException;

    Set<ListaNomePessoaJuridicaDTO> getNomesPessoasJuridicas();

}
