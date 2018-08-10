package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.mme.enumeration.TpBoleto;

public class BoletoRelatorioPagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    private String nomeFantasia;

    private BigDecimal valorBoleto;

    private Integer mesReferencia;

    private LocalDate dataVencimento;

    private String statusBoleto;


    public BoletoRelatorioPagamentoDTO(String cnpj, String nomeFantasia, BigDecimal valorBoleto, Integer mesReferencia,
            LocalDate dataVencimento, TpBoleto statusBoleto) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.valorBoleto = valorBoleto;
        this.mesReferencia = mesReferencia;
        this.dataVencimento = dataVencimento;
        this.statusBoleto = statusBoleto.status();
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

    public Integer getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Integer mesReferencia) {
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
