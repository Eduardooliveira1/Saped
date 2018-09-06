package br.gov.mme.service.dto;

import br.gov.mme.enumeration.TpStatusBoleto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CobrancaBoletoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime dataVencimento;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataSegundaVia;
    private BigDecimal valorBoleto;
    private String cnpj;
    private TpStatusBoleto statusBoleto;
    private BigDecimal mesReferencia;
    private BigDecimal anoReferencia;

    public CobrancaBoletoDTO(LocalDateTime dataVencimento,
                             BigDecimal valorBoleto,
                             String cnpj,
                             TpStatusBoleto statusBoleto,
                             BigDecimal mesReferencia,
                             BigDecimal anoReferencia,
                             LocalDateTime dataPagamento,
                             LocalDateTime dataSegundaVia) {
        this.dataVencimento = dataVencimento;
        this.valorBoleto = valorBoleto;
        this.cnpj = cnpj;
        this.statusBoleto = statusBoleto;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.dataPagamento = dataPagamento;
        this.dataSegundaVia = dataSegundaVia;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public LocalDateTime getDataSegundaVia() {
        return dataSegundaVia;
    }

    public void setDataSegundaVia(LocalDateTime dataSegundaVia) {
        this.dataSegundaVia = dataSegundaVia;
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