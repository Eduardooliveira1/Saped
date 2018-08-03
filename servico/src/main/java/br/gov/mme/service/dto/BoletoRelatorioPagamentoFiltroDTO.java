package br.gov.mme.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.gov.mme.enumeration.StatusPagamento;

public class PessoaJuridicaRelatorioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;
    private Double valor;
    private LocalDate mesReferencia;
    private LocalDate dataVencimento;
    private StatusPagamento status;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
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

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

}
