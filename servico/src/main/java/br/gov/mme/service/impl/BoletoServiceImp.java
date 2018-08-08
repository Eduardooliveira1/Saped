package br.gov.mme.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.domain.Boleto;
import br.gov.mme.enumeration.ReportType;
import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.repository.BoletoRepository;
import br.gov.mme.service.BoletoService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoVO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.mapper.BoletoMapper;
import br.gov.mme.service.util.JasperUtils;
import net.sf.jasperreports.engine.JRException;

/**
 * Service Implementation for managing Boleto.
 */
@Service
@Transactional
public class BoletoServiceImp implements BoletoService{

    private final BoletoMapper boletoMapper;

    private final BoletoRepository boletoRepository;

    private static InputStream inputFileStream = BoletoServiceImp.class
            .getResourceAsStream("/reports/PagamentoReport.jrxml");

    private final Logger log = LoggerFactory.getLogger(BoletoServiceImp.class);

    public BoletoServiceImp(BoletoMapper boletoMapper, BoletoRepository boletoRepository) {
        this.boletoMapper = boletoMapper;
        this.boletoRepository = boletoRepository;
    }

    @Override
    public List<BoletoRelatorioPagamentoVO> converterFiltroToVO(BoletoRelatorioPagamentoFiltroDTO filtro) {
        HashSet<Boleto> boletoHash = new HashSet<>(boletoRepository.listarPagamentosRelatorioExport(filtro));
        List<BoletoRelatorioPagamentoVO> dadosRel = new ArrayList<>();
        boletoHash.forEach(boleto -> {
            BoletoRelatorioPagamentoVO instanciaRel = boletoMapper.toDTO(boleto);
            dadosRel.add(instanciaRel);
        });
        return dadosRel;
    }

    @Override
    public byte[] getRelatorio(List<BoletoRelatorioPagamentoVO> voList)
            throws CheckedInvalidArgumentException, RelatorioException, LeituraBufferException {
        try {
            return Base64.getEncoder()
                    .encode(JasperUtils.getReportData(inputFileStream, ReportType.XLS, voList).toByteArray());
        } catch (JRException e) {
            log.error(e.getMessage(), e);
            throw new RelatorioException(e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new LeituraBufferException(e);
        }
    }

    @Override
    public Page<BoletoRelatorioPagamentoVO> listarPagamentos(Pageable pageable) {
        return boletoRepository.listarPagamentosRelatorio(pageable);
        } 

}
