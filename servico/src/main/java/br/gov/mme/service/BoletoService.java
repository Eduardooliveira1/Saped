package br.gov.mme.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.gov.mme.exceptions.ArquivoDeTipoInvalidoException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

public interface BoletoService {

    List<BoletoRelatorioPagamentoDTO> listarPagamentosRelatorio(BoletoRelatorioPagamentoFiltroDTO filtro);

    void getRelatorio(BoletoRelatorioPagamentoFiltroDTO filtro, HttpServletResponse response)
            throws ArquivoDeTipoInvalidoException, RelatorioException, LeituraBufferException;

}
