package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.basis.dynamicexports.pojo.ReportObject;

public class BoletoRelatorioPagamentoDTO implements Serializable, ReportObject {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    private String nomeFantasia;

    private BigDecimal valorBoleto;

    private String mesReferencia;

    private LocalDate dataVencimento;

    private LocalDate dataSegundaVia;

    private String statusBoleto;

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

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataSegundaVia() {
        return dataSegundaVia;
    }

    public void setDataSegundaVia(LocalDate dataSegundaVia) {
        this.dataSegundaVia = dataSegundaVia;
    }

    public String getStatusBoleto() {
        return statusBoleto;
    }

    public void setStatusBoleto(String statusBoleto) {
        this.statusBoleto = statusBoleto;
    }

}
