package br.gov.mme.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoVO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.dto.ListaNomePessoaJuridicaDTO;

/**
 * Service Interface for managing Relatorio.
 * 
 * @see RelatorioServiceImp
 */
public interface RelatorioService {

    Page<BoletoRelatorioPagamentoVO> listarPagamentos(Pageable pageable);

    List<BoletoRelatorioPagamentoVO> converterFiltroToVO(BoletoRelatorioPagamentoFiltroDTO filtro);

    byte[] getRelatorio(List<BoletoRelatorioPagamentoVO> voList)
            throws CheckedInvalidArgumentException, RelatorioException, LeituraBufferException;

    Set<ListaNomePessoaJuridicaDTO> getNomesPessoasJuridicas();

}
