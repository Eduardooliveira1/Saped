package br.gov.mme.service.dto;

import br.gov.mme.enumeration.TpStatusBoleto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CobrancaBoletoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date dataVencimento;
    private Date dataPagamento;
    private Date dataSegundaVia;
    private BigDecimal valorBoleto;
    private String cnpj;
    private TpStatusBoleto statusBoleto;
    private Date dataHoraStatusBoleto;
    private BigDecimal mesReferencia;
    private BigDecimal anoReferencia;

    public CobrancaBoletoDTO(Date dataVencimento, BigDecimal valorBoleto, String cnpj, TpStatusBoleto statusBoleto, Date dataHoraStatusBoleto, BigDecimal mesReferencia, BigDecimal anoReferencia, Date dataPagamento, Date dataSegundaVia) {
        this.dataVencimento = dataVencimento;
        this.valorBoleto = valorBoleto;
        this.cnpj = cnpj;
        this.statusBoleto = statusBoleto;
        this.dataHoraStatusBoleto = dataHoraStatusBoleto;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.dataPagamento = dataPagamento;
        this.dataSegundaVia = dataSegundaVia;
    }

    public Date getDataSegundaVia() {
        return dataSegundaVia;
    }

    public void setDataSegundaVia(Date dataSegundaVia) {
        this.dataSegundaVia = dataSegundaVia;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public void setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public TpStatusBoleto getStatusBoleto() {
        return statusBoleto;
    }

    public void setStatusBoleto(TpStatusBoleto statusBoleto) {
        this.statusBoleto = statusBoleto;
    }

    public Date getDataHoraStatusBoleto() {
        return dataHoraStatusBoleto;
    }

    public void setDataHoraStatusBoleto(Date dataHoraStatusBoleto) {
        this.dataHoraStatusBoleto = dataHoraStatusBoleto;
    }

    public BigDecimal getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(BigDecimal mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public BigDecimal getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(BigDecimal anoReferencia) {
        this.anoReferencia = anoReferencia;
    }
}