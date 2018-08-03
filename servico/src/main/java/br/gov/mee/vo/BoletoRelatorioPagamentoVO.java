package br.gov.mee.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.gov.mme.enumeration.TpStatusBoleto;

public class BoletoRelatorioPagamentoVO implements ReportVO, Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    private String nomeFantasia;

    private BigDecimal valor;

    private LocalDate mesReferencia;

    private LocalDate dataVencimento;

    private LocalDate dataSegundaVia;

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

    public LocalDate getDataSegundaVia() {
        return dataSegundaVia;
    }

    public void setDataSegundaVia(LocalDate dataSegundaVia) {
        this.dataSegundaVia = dataSegundaVia;
    }

    public TpStatusBoleto getStatus() {
        return status;
    }

    public void setStatus(TpStatusBoleto status) {
        this.status = status;
    }

    @Override
    public Object[] toArray() {
        return new Object[] { cnpj, nomeFantasia, valor, mesReferencia, dataVencimento, 
                dataSegundaVia, status };
    }

}
