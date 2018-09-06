package br.gov.mme.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.service.dto.CobrancaBoletoDTO;
import br.gov.mme.service.dto.CobrancaDTO;
import br.gov.mme.service.dto.DadosGerarBoletoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ListUtils;

import br.com.basis.nativequerybuilder.service.NativeQueryService;
import br.gov.mme.enumeration.ReportType;
import br.gov.mme.exceptions.ArquivoDeTipoInvalidoException;
import br.gov.mme.exceptions.FiltroVazioException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.nativequery.RelatorioPagamentoNativeQuery;
import br.gov.mme.service.BoletoService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.util.JasperUtils;
import br.gov.mme.web.rest.util.PaginationUtil;

@Service
@Transactional
public class BoletoServiceImpl implements BoletoService{

    private final NativeQueryService nativeQueryService;

    private static final String RELATORIO_TEMPLATE = "/reports/PagamentoReport.jrxml";

    private final Logger log = LoggerFactory.getLogger(BoletoServiceImpl.class);

    public BoletoServiceImpl(NativeQueryService nativeQueryService) {
        this.nativeQueryService = nativeQueryService;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    private Page<BoletoRelatorioPagamentoDTO> listarTodosPagamentos(Pageable pageable) {
        return (Page<BoletoRelatorioPagamentoDTO>) nativeQueryService
                .filterPage(new RelatorioPagamentoNativeQuery(), PaginationUtil.ignoreCase(pageable));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public Page<BoletoRelatorioPagamentoDTO> listarPagamentosRelatorio(BoletoRelatorioPagamentoFiltroDTO filtro,
            Pageable pageable) throws FiltroVazioException {
        if (!filtro.getHasFiltro()) {
            return this.listarTodosPagamentos(pageable);
        }
        if (this.isEmpty(filtro)) {
            throw new FiltroVazioException();
        }
        return (Page<BoletoRelatorioPagamentoDTO>) nativeQueryService
                .filterPage(new RelatorioPagamentoNativeQuery(filtro),
                        PaginationUtil.ignoreCase(pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public void getRelatorioExport(BoletoRelatorioPagamentoFiltroDTO filtro, HttpServletResponse response)
            throws ArquivoDeTipoInvalidoException, RelatorioException, 
            LeituraBufferException, FiltroVazioException {
        List<BoletoRelatorioPagamentoDTO> result = listarPagamentosRelatorio(filtro, null).getContent();
        JasperUtils.getReportData(RELATORIO_TEMPLATE, ReportType.XLS, result, response, log);
    }

    private boolean isEmpty(BoletoRelatorioPagamentoFiltroDTO filtro) {
        return (filtro.getDataVencimento() == null && ListUtils.isEmpty(filtro.getIdsPessoasJuridicas())
                && filtro.getMesReferencia() == null && filtro.getTpStatusBoleto() == null
                && filtro.getValor() == null);
    }

}
