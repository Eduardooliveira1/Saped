package br.gov.mme.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mee.vo.BoletoRelatorioPagamentoVO;
import br.gov.mme.domain.Boleto;
import br.gov.mme.repository.BoletoRepository;
import br.gov.mme.service.BoletoService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.mapper.BoletoMapper;

/**
 * Service Implementation for managing Boleto.
 */
@Service
@Transactional
public class BoletoServiceImp implements BoletoService{

    private final BoletoMapper boletoMapper;

    private final BoletoRepository boletoRepository;

    public BoletoServiceImp(BoletoMapper boletoMapper, BoletoRepository boletoRepository) {
        this.boletoMapper = boletoMapper;
        this.boletoRepository = boletoRepository;
    }

    @Override
    public List<BoletoRelatorioPagamentoVO> converterFiltroToVO(BoletoRelatorioPagamentoFiltroDTO filtro) {
        HashSet<Boleto> boletoHash = new HashSet<>(boletoRepository.listarPagamentosRelatorioExport(filtro));
        List<BoletoRelatorioPagamentoVO> dadosRel = new ArrayList<>();
        boletoHash.forEach(boleto -> {
            BoletoRelatorioPagamentoVO instanciaRel = boletoMapper.toVO(boleto);
            dadosRel.add(instanciaRel);
        });
        return dadosRel;
    }

    @Override
    public ByteArrayOutputStream getRelatorio(List<BoletoRelatorioPagamentoVO> voList) {
        return new ByteArrayOutputStream();
    }
    


}
