package br.gov.mme.service;

import javax.servlet.http.HttpServletResponse;

import br.gov.mme.service.dto.CobrancaDTO;
import br.gov.mme.service.dto.DadosGerarBoletoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.exceptions.ArquivoDeTipoInvalidoException;
import br.gov.mme.exceptions.FiltroVazioException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

public interface BoletoService {

    Page<BoletoRelatorioPagamentoDTO> listarPagamentosRelatorio(BoletoRelatorioPagamentoFiltroDTO filtro,
            Pageable pageable) throws FiltroVazioException;

    void getRelatorioExport(BoletoRelatorioPagamentoFiltroDTO filtro, HttpServletResponse response)
            throws ArquivoDeTipoInvalidoException, RelatorioException, LeituraBufferException, 
            FiltroVazioException;
}
