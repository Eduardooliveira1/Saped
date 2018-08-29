package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.gov.mme.service.util.DateUtils;

public class BoletoRelatorioPagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    private String nomeFantasia;

    private BigDecimal valorBoleto;

    private String mesReferencia;

    private String dataVencimento;

    private String dataSegundaVia;

    private String statusBoleto;

    public BoletoRelatorioPagamentoDTO(String cnpj, String nomeFantasia, BigDecimal valorBoleto, 
            Integer mesReferencia, Integer anoReferencia, String dataSegundaVia, String statusBoleto,
            String dataVencimento) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.valorBoleto = valorBoleto;
        this.mesReferencia = mesReferencia != null
                ? DateUtils.convertMesAnoReferenciaToString(mesReferencia - 1, anoReferencia)
                : null;
        this.dataVencimento = dataVencimento;
        this.statusBoleto = statusBoleto;
        this.dataSegundaVia = dataSegundaVia;
    }

    public BoletoRelatorioPagamentoDTO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public void setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDataSegundaVia() {
        return dataSegundaVia;
    }

    public void setDataSegundaVia(String dataSegundaVia) {
        this.dataSegundaVia = dataSegundaVia;
    }

    public String getStatusBoleto() {
        return statusBoleto;
    }

    public void setStatusBoleto(String statusBoleto) {
        this.statusBoleto = statusBoleto;
    }

}
