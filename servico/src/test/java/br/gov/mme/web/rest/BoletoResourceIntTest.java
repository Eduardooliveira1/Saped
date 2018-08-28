package br.gov.mme.web.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import br.gov.mme.domain.Boleto;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.domain.StatusBoleto;
import br.gov.mme.enumeration.FlSituacaoBancaria;
import br.gov.mme.enumeration.TpBoleto;
import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.utils.TestUtils;

public class BoletoResourceIntTest {
    
    public static Boleto getDefaultEntity(EntityManager em) {
        PessoaJuridica pessoaJuridica = PessoaJuridicaResourceIntTest.createDefaultEntity();
        TestUtils.saveAndFlush(pessoaJuridica, em);
        return getEntity(em, null, TestUtils.LOCAL_DATE_NOW, TestUtils.DEFAULT_BIGDECIMAL_TAM_9_0,
                TestUtils.DEFAULT_LONG_TAM_6, TpBoleto.AD, pessoaJuridica,
                FlSituacaoBancaria.BOLETO_EMITIDO, TestUtils.LOCAL_DATE_TIME_NOW, TpStatusBoleto.AV, 
                null, null);
    }
    
    public static Boleto getDiferentEntity(EntityManager em) {
        PessoaJuridica pessoaJuridica = PessoaJuridicaResourceIntTest.createDiferentEntity();
        TestUtils.saveAndFlush(pessoaJuridica, em);
        return getEntity(em, null, TestUtils.UPDATED_LOCAL_DATE, TestUtils.UPDATED_BIGDECIMAL_TAM_9_0,
                TestUtils.UPDATED_LONG_TAM_6, TpBoleto.SV, pessoaJuridica, FlSituacaoBancaria.CONVENIO_INATIVO,
                TestUtils.LOCAL_DATE_TIME_IN_2_YEARS, TpStatusBoleto.VE, TestUtils.DEFAULT_INTEGER_TAM_2,
                TestUtils.DEFAULT_INTEGER_TAM_4);
    }
    
    public static Boleto getWithCustomCNPJEntity(EntityManager em, String cnpj) {
        PessoaJuridica pessoaJuridica = PessoaJuridicaResourceIntTest
                .createEntity(TestUtils.UPDATED_STRING_TAM_9, cnpj);
        TestUtils.saveAndFlush(pessoaJuridica, em);
        return getEntity(em, null, TestUtils.LOCAL_DATE_NOW, TestUtils.DEFAULT_BIGDECIMAL_TAM_9_0,
                TestUtils.DEFAULT_LONG_TAM_6, TpBoleto.AD, pessoaJuridica,
                FlSituacaoBancaria.BOLETO_EMITIDO, TestUtils.LOCAL_DATE_TIME_NOW, TpStatusBoleto.AV,
                null, null);
    }

    public static Boleto getEntity(EntityManager em, Long id, LocalDate dataVencimento, BigDecimal valorBoleto,
            Long nossoNumero, TpBoleto tpBoleto, PessoaJuridica pessoaJuridica,
            FlSituacaoBancaria flSituacaoBancaria, LocalDateTime dataHoraStatus,
            TpStatusBoleto tpStatusBoleto, Integer mesReferencia, Integer anoReferencia) {
        Boleto boleto = new Boleto();
        boleto.setDataVencimento(dataVencimento);
        boleto.setValorBoleto(valorBoleto);
        boleto.setNossoNumero(nossoNumero);
        boleto.setPessoaJuridica(pessoaJuridica);
        boleto.setTpBoleto(tpBoleto);

        if (id != null) {
            boleto.setId(id);
        }

        if (mesReferencia != null) {
            boleto.setMesReferencia(mesReferencia);
        }

        if (anoReferencia != null) {
            boleto.setAnoReferencia(anoReferencia);
        }

        StatusBoleto statusBoleto = StatusBoletoResourceIntTest.getEntity(boleto, flSituacaoBancaria, 
                dataHoraStatus, tpStatusBoleto);
        TestUtils.saveAndFlush(statusBoleto, em);

        return boleto;
    }

}

