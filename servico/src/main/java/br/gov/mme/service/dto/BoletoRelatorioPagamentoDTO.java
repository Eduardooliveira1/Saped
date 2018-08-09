package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import br.com.basis.dynamicexports.pojo.ReportObject;
import br.gov.mme.enumeration.TpBoleto;

public class BoletoRelatorioPagamentoDTO implements Serializable, ReportObject {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    private String nomeFantasia;

    private BigDecimal valorBoleto;

    private BigInteger mesReferencia;

    private LocalDate dataVencimento;

    public BoletoRelatorioPagamentoDTO(String cnpj, String nomeFantasia, BigDecimal valorBoleto,
            BigInteger mesReferencia,
            LocalDate dataVencimento, TpBoleto statusBoleto) {
        super();
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.valorBoleto = valorBoleto;
        this.mesReferencia = mesReferencia;
        this.dataVencimento = dataVencimento;
        this.statusBoleto = statusBoleto.status();
    }

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

    public BigInteger getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(BigInteger mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getStatusBoleto() {
        return statusBoleto;
    }

    public void setStatusBoleto(String statusBoleto) {
        this.statusBoleto = statusBoleto;
    }

}
