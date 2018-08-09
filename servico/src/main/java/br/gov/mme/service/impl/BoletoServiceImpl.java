package br.gov.mme.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.enumeration.ReportType;
import br.gov.mme.exceptions.ArquivoDeTipoInvalidoException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.repository.BoletoRepository;
import br.gov.mme.service.BoletoService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.util.JasperUtils;

@Service
@Transactional
public class BoletoServiceImpl implements BoletoService{

    private final BoletoRepository boletoRepository;

    private static final String RELATORIO_TEMPLATE = "/reports/PagamentoReport.jrxml";

    private final Logger log = LoggerFactory.getLogger(BoletoServiceImpl.class);

    public BoletoServiceImpl(BoletoRepository boletoRepository) {
        this.boletoRepository = boletoRepository;
    }

    @Override
    public List<BoletoRelatorioPagamentoDTO> listarPagamentosRelatorio(BoletoRelatorioPagamentoFiltroDTO filtro) {
        return boletoRepository.listarPagamentosRelatorio(filtro);
    }

    @Override
    public void getRelatorio(BoletoRelatorioPagamentoFiltroDTO filtro, HttpServletResponse response)
            throws ArquivoDeTipoInvalidoException, RelatorioException, LeituraBufferException {
        List<BoletoRelatorioPagamentoDTO> result = listarPagamentosRelatorio(filtro);
        JasperUtils.getReportData(RELATORIO_TEMPLATE, ReportType.XLS, result, response, log);
    }

}
