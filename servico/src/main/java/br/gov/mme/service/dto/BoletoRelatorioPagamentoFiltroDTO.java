package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.mme.enumeration.TpStatusBoleto;

public class BoletoRelatorioPagamentoFiltroDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    private String nomeFantasia;

    private BigDecimal valor;

    private LocalDate mesReferencia;

    private LocalDate dataVencimento;

    private TpStatusBoleto status;

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
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(LocalDate mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public TpStatusBoleto getStatus() {
        return status;
    }
    public void setStatus(TpStatusBoleto status) {
        this.status = status;
    }

}