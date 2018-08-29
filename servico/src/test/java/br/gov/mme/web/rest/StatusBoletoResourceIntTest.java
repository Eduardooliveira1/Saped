package br.gov.mme.web.rest;

import java.time.LocalDateTime;

import br.gov.mme.domain.Boleto;
import br.gov.mme.domain.StatusBoleto;
import br.gov.mme.enumeration.FlSituacaoBancaria;
import br.gov.mme.enumeration.TpStatusBoleto;

public class StatusBoletoResourceIntTest {


    public static StatusBoleto getEntity(Boleto boleto, FlSituacaoBancaria flSituacaoBancaria,
            LocalDateTime dataHoraStatus, TpStatusBoleto tpStatusBoleto) {
        StatusBoleto statusBoleto = new StatusBoleto();
        statusBoleto.setSituacaoBancaria(flSituacaoBancaria);
        statusBoleto.setDataHoraStatus(dataHoraStatus);
        statusBoleto.setTpStatusBoleto(tpStatusBoleto);
        statusBoleto.setBoleto(boleto);

        return statusBoleto;
    }

}
