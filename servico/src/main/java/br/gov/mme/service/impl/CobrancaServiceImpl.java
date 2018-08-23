package br.gov.mme.service.impl;

import br.gov.mme.enumeration.AcaoGerarBoleto;
import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.service.CobrancaService;
import br.gov.mme.service.dto.CobrancaBoletoDTO;
import br.gov.mme.service.dto.CobrancaDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
public class CobrancaServiceImpl implements CobrancaService {

    @Override
    public List<CobrancaDTO> obterCobrancasDoAno(int ano, Long idPj) {
        List<CobrancaDTO> cobrancasDoAno = new ArrayList<>();
        Date diaAtual = obterDiaAtual();

        List<CobrancaBoletoDTO> boletosCadastradosNoAnoCorrente = obtemBoletosCadastrados(ano, idPj);


        for (int mes = 1; mes <= 12; mes++) {
            CobrancaBoletoDTO dadosBoleto = obtemDadosBoletoMesCorrente(boletosCadastradosNoAnoCorrente, mes);

            if (dadosBoleto != null) {
                cobrancasDoAno.add(criaCobrancaComDadosDoBoleto(dadosBoleto, mes, ano));
            } else {
                cobrancasDoAno.add(cobrançaPadrao(mes, ano, diaAtual, idPj));
            }
        }
        return cobrancasDoAno;
    }

    private CobrancaDTO cobrançaPadrao(int mes, int ano, Date diaAtual, Long idPj) {
        CobrancaDTO cobrancaMensal = new CobrancaDTO();

        Date dataVencimento = obterQuintoDiaUtil(mes, ano);

        if (diaAtual.after(dataVencimento)) {
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

    private CobrancaDTO criaCobrancaComDadosDoBoleto(CobrancaBoletoDTO dadosBoleto, int mes, int ano) {

        CobrancaDTO cobrancaMensal = new CobrancaDTO();

        cobrancaMensal.setDataVencimento(converteDataParaString(obterQuintoDiaUtil(mes, ano)));
        cobrancaMensal.setDataPagamento(converteDataParaString(dadosBoleto.getDataPagamento()));
        cobrancaMensal.setDataSegundaVia(converteDataParaString(dadosBoleto.getDataSegundaVia()));
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


    private List<CobrancaBoletoDTO> obtemBoletosCadastrados(int ano, Long idPj) {
        //TODO:: obtém todos os boletos relacionados à uma pessoa jurídica (idPj) no determinado ano (list cobrança)
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        //TODO:: REMOVER ESTA LISTA E ESTA COBRANCA QUE SÃO GERADAS NAS PRÓXIMAS LINHAS
        List<CobrancaBoletoDTO> boletosCadastrados = new ArrayList<>();
        CobrancaBoletoDTO boletoCobranca = new CobrancaBoletoDTO(today.getTime(),
                                                                new BigDecimal(123),
                                                            "123455687877",
                                                                TpStatusBoleto.PG,
                                                                today.getTime(), new BigDecimal(8),
                                                                new BigDecimal(2018),
                                                                today.getTime(),today.getTime());

        boletosCadastrados.add(boletoCobranca);
        return boletosCadastrados;
    }

    private String converteDataParaString(Date data) {
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        return sdfr.format(data);
    }

    public Date obterQuintoDiaUtil(int mes, int ano) {

        int contadorDeDiasUteis = 0;
        Date quintoDiaUtil = new Date();

        Calendar calendario = Calendar.getInstance();

        for (int dia = 1; dia <= 31; dia++) {
            calendario = setaDia(calendario, dia, mes, ano);
            if ((calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
                    && (calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
                contadorDeDiasUteis++;
                if (contadorDeDiasUteis == 5) {
                    quintoDiaUtil = calendario.getTime();
                }
            }
        }
        return quintoDiaUtil;
    }

    private Date obterDiaAtual() {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        return today.getTime();
    }

    private Calendar setaDia(Calendar calendario, int dia, int mes, int ano) {
        calendario.set(Calendar.YEAR, ano);
        calendario.set(Calendar.MONTH, mes - 1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);
        return calendario;
    }
}
