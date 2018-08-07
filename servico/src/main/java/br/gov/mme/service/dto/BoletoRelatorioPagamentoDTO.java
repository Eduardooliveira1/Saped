package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import br.gov.mme.enumeration.TpBoleto;

public class BoletoRelatorioPagamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public BoletoRelatorioPagamentoDTO(String cnpj, String nomeFantasia, BigDecimal valorBoleto, 
            BigInteger mesReferencia, LocalDate dataVencimento, LocalDate dataSegundaVia, TpBoleto tpBoleto) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.valorBoleto = valorBoleto;
        this.mesReferencia = mesReferencia;
        this.dataVencimento = dataVencimento;
        this.dataSegundaVia = dataSegundaVia;
        this.tpBoleto = tpBoleto;
    }
    public BoletoRelatorioPagamentoDTO() {

    }

    private String cnpj;

    private String nomeFantasia;

    private BigDecimal valorBoleto;

    private BigInteger mesReferencia;

    private LocalDate dataVencimento;

    private LocalDate dataSegundaVia;

    private TpBoleto tpBoleto;

    public String getCnpj() {
        return cnpj;
    }

    public BoletoRelatorioPagamentoDTO setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public BoletoRelatorioPagamentoDTO setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
        return this;
    }

    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public BoletoRelatorioPagamentoDTO setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
        return this;
    }

    public BigInteger getMesReferencia() {
        return mesReferencia;
    }

    public BoletoRelatorioPagamentoDTO setMesReferencia(BigInteger mesReferencia) {
        this.mesReferencia = mesReferencia;
        return this;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public BoletoRelatorioPagamentoDTO setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
        return this;
    }

    public LocalDate getDataSegundaVia() {
        return dataSegundaVia;
    }

    public BoletoRelatorioPagamentoDTO setDataSegundaVia(LocalDate dataSegundaVia) {
        this.dataSegundaVia = dataSegundaVia;
        return this;
    }

    public TpBoleto getTpBoleto() {
        return tpBoleto;
    }

    public BoletoRelatorioPagamentoDTO setTpBoleto(TpBoleto tpBoleto) {
        this.tpBoleto = tpBoleto;
        return this;
    }

}
