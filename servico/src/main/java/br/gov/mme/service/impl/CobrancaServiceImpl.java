package br.gov.mme.service.impl;

import br.com.basis.nativequerybuilder.service.NativeQueryService;
import br.gov.mme.enumeration.AcaoGerarBoleto;
import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.nativequery.CobrancaBoletoNativeQuery;
import br.gov.mme.service.CobrancaService;
import br.gov.mme.service.dto.CobrancaBoletoDTO;
import br.gov.mme.service.dto.CobrancaDTO;
import br.gov.mme.service.dto.DadosGerarBoletoDTO;
import br.gov.mme.service.dto.FiltroListagemCobrancaDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
public class CobrancaServiceImpl implements CobrancaService {

    NativeQueryService nativeQueryService;

    public CobrancaServiceImpl(NativeQueryService nativeQueryService) {
        this.nativeQueryService = nativeQueryService;
    }
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    private List<CobrancaBoletoDTO> listarBoletosDoAno(FiltroListagemCobrancaDTO filtro) {
        return (List<CobrancaBoletoDTO>) nativeQueryService
                .filterList(new CobrancaBoletoNativeQuery(filtro));
    }

    @Override
    public List<CobrancaDTO> obterCobrancasDoAno(FiltroListagemCobrancaDTO filtro) {
        List<CobrancaDTO> cobrancasDoAno = new ArrayList<>();
        LocalDate diaAtual = obterDiaAtual();

        List<CobrancaBoletoDTO> boletosCadastradosNoAnoCorrente = obtemBoletosCadastrados(filtro);


        for (int mes = 1; mes <= 12; mes++) {
            CobrancaBoletoDTO dadosBoleto = obtemDadosBoletoMesCorrente(boletosCadastradosNoAnoCorrente, mes);

            if (dadosBoleto != null) {
                cobrancasDoAno.add(criaCobrancaComDadosDoBoleto(dadosBoleto, mes, filtro.getAno()));
            } else {
                cobrancasDoAno.add(cobrançaPadrao(mes, filtro.getAno(), diaAtual, filtro.getIdPj()));
            }
        }
        return cobrancasDoAno;
    }

    @Override
    public CobrancaDTO gerarBoleto(DadosGerarBoletoDTO dadosDoBoleto) {
        // TODO::Olhar este método do native query
        CobrancaBoletoDTO boletoGerado = new CobrancaBoletoDTO(LocalDateTime.now(),
                new BigDecimal(123),
                "213123123213",
                TpStatusBoleto.PG,
                new BigDecimal(9),
                new BigDecimal(2018),
                LocalDateTime.now(),
                LocalDateTime.now());
        CobrancaDTO cb = criaCobrancaComDadosDoBoleto(boletoGerado,9,2018);
        return cb;
    }

    private CobrancaDTO cobrançaPadrao(int mes, long ano, LocalDate diaAtual, Long idPj) {
        CobrancaDTO cobrancaMensal = new CobrancaDTO();

        LocalDate dataVencimento = obterQuintoDiaUtil(mes, ano);

        if (diaAtual.isAfter(dataVencimento)) {
            cobrancaMensal.setStatus(TpStatusBoleto.VE);
        } else {
            cobrancaMensal.setStatus(TpStatusBoleto.AV);
        }

        cobrancaMensal.setAcaoGerar(AcaoGerarBoleto.EMITIR);
        cobrancaMensal.setDataVencimento(converteDataParaString(dataVencimento));
        cobrancaMensal.setHabilitaExcluir(false);
        cobrancaMensal.setHabilitaImprimir(false);
        cobrancaMensal.setHabilitarGerarBoleto(true);
        cobrancaMensal.setHabilitarInserirValor(true);
        cobrancaMensal.setIdPj(idPj);
        return cobrancaMensal;
    }

    private CobrancaDTO criaCobrancaComDadosDoBoleto(CobrancaBoletoDTO dadosBoleto, int mes, long ano) {

        CobrancaDTO cobrancaMensal = new CobrancaDTO();

        cobrancaMensal.setDataVencimento(converteDataParaString(obterQuintoDiaUtil(mes, ano)));
        cobrancaMensal.setDataPagamento(converteDataParaString(dadosBoleto.getDataPagamento().toLocalDate()));
        cobrancaMensal.setDataSegundaVia(converteDataParaString(dadosBoleto.getDataSegundaVia().toLocalDate()));
        //TODO::Olhar se tem data da segunda via na tabela
        cobrancaMensal.setValor(dadosBoleto.getValorBoleto().toString());
        cobrancaMensal.setAcaoGerar(obterAcaoGerarBoleto(dadosBoleto));
        cobrancaMensal.setHabilitaImprimir(obterAcaoDeHabilitarImprimirBoleto(dadosBoleto));
        cobrancaMensal.setHabilitaExcluir(obterAcaoDeHabilitarExcluirBoleto(dadosBoleto));
        cobrancaMensal.setStatus(obterStatusBoleto(dadosBoleto));
        cobrancaMensal.setHabilitarInserirValor(false);
        cobrancaMensal.setHabilitarGerarBoleto(obterPermissaoGerarBoleto(dadosBoleto));

        return cobrancaMensal;

    }

    private Boolean obterPermissaoGerarBoleto(CobrancaBoletoDTO dadosBoleto) {
        if(dadosBoleto.getStatusBoleto() == TpStatusBoleto.AV) {
            return false;
        } else if(dadosBoleto.getStatusBoleto() == TpStatusBoleto.VE) {
            return true;
        }

        return false;
    }


    private TpStatusBoleto obterStatusBoleto(CobrancaBoletoDTO dadosBoleto) {
        return dadosBoleto.getStatusBoleto();
    }

    private Boolean obterAcaoDeHabilitarExcluirBoleto(CobrancaBoletoDTO dadosBoleto) {
        if(dadosBoleto.getStatusBoleto() == TpStatusBoleto.PG
           || dadosBoleto.getStatusBoleto() == TpStatusBoleto.VE) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean obterAcaoDeHabilitarImprimirBoleto(CobrancaBoletoDTO dadosBoleto) {
        return true;
    }

    private AcaoGerarBoleto obterAcaoGerarBoleto(CobrancaBoletoDTO dadosBoleto) {
        if(dadosBoleto.getStatusBoleto() == TpStatusBoleto.AV) {
            return AcaoGerarBoleto.EMITIR;
        } else {
            return AcaoGerarBoleto.SEGUNDAVIA;
        }
    }

    private CobrancaBoletoDTO obtemDadosBoletoMesCorrente(List<CobrancaBoletoDTO> boletosCadastrados, int mes) {
        try {
            return boletosCadastrados.stream()
                    .filter(item -> item.getMesReferencia().intValue() == mes)
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }


    private List<CobrancaBoletoDTO> obtemBoletosCadastrados(FiltroListagemCobrancaDTO filtro) {
        List<CobrancaBoletoDTO> cb = new ArrayList<>();
        return cb;
        //TODO::OLhar a exceção que está dando no nativequery
        //return listarBoletosDoAno(filtro);
    }

    private String converteDataParaString(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return  data.format(formatter);
    }

    public LocalDate obterQuintoDiaUtil(int mes, long ano) {

        int contadorDeDiasUteis = 0;
        LocalDate quintoDiaUtil = null;
        LocalDate diaDoMes;

        for (int dia = 1; dia <= 31; dia++) {
            diaDoMes = setaDia(dia, mes, ano);
            if ((diaDoMes.getDayOfWeek() != DayOfWeek.SATURDAY)
                    && (diaDoMes.getDayOfWeek() != DayOfWeek.SUNDAY)) {
                contadorDeDiasUteis++;
                if (contadorDeDiasUteis == 5) {
                    quintoDiaUtil = diaDoMes;
                    break;
                }
            }
        }

        return quintoDiaUtil ;
    }

    private LocalDate obterDiaAtual() {
        return LocalDate.now();
    }

    private LocalDate setaDia(int dia, int mes, long ano) {
        return LocalDate.of((int )ano, mes, dia);
    }
}
