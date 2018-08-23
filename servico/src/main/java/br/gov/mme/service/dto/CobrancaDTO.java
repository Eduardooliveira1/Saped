package br.gov.mme.service.dto;

import br.gov.mme.enumeration.AcaoGerarBoleto;
import br.gov.mme.enumeration.TpStatusBoleto;

import java.io.Serializable;

public class CobrancaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long idPj;
    private String mesReferencia;
    private String dataVencimento;
    private String dataPagamento;
    private String dataSegundaVia;
    private String valor;
    private AcaoGerarBoleto acaoGerar;
    private Boolean habilitaImprimir;
    private Boolean habilitaExcluir;
    private Boolean habilitarGerarBoleto;
    private Boolean habilitarInserirValor;
    private TpStatusBoleto status;

    public void setIdPj(Long idPj) {
        this.idPj = idPj;
    }

    public Long getIdPj() {
        return idPj;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public CobrancaDTO() { }

    public CobrancaDTO(String mesReferencia, String dataPagamento, String dataSegundaVia, String valor, AcaoGerarBoleto acaoGerar, Boolean habilitaImprimir, Boolean habilitaExcluir, TpStatusBoleto status, Boolean habilitarGerarBoleto, Long idPj, Boolean habilitarInserirValor) {
        this.mesReferencia = mesReferencia;
        this.dataPagamento = dataPagamento;
        this.dataSegundaVia = dataSegundaVia;
        this.valor = valor;
        this.acaoGerar = acaoGerar;
        this.habilitaImprimir = habilitaImprimir;
        this.habilitaExcluir = habilitaExcluir;
        this.status = status;
        this.habilitarGerarBoleto = habilitarGerarBoleto;
        this.idPj = idPj;
        this.habilitarInserirValor = habilitarInserirValor;
    }

    public Boolean getHabilitarInserirValor() {
        return habilitarInserirValor;
    }

    public void setHabilitarInserirValor(Boolean habilitarInserirValor) {
        this.habilitarInserirValor = habilitarInserirValor;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDataSegundaVia() {
        return dataSegundaVia;
    }

    public void setDataSegundaVia(String dataSegundaVia) {
        this.dataSegundaVia = dataSegundaVia;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public AcaoGerarBoleto getAcaoGerar() {
        return acaoGerar;
    }

    public void setAcaoGerar(AcaoGerarBoleto acaoGerar) {
        this.acaoGerar = acaoGerar;
    }

    public Boolean getHabilitaImprimir() {
        return habilitaImprimir;
    }

    public Boolean getHabilitarGerarBoleto() {
        return habilitarGerarBoleto;
    }

    public void setHabilitarGerarBoleto(Boolean habilitarGerarBoleto) {
        this.habilitarGerarBoleto = habilitarGerarBoleto;
    }

    public void setHabilitaImprimir(Boolean habilitaImprimir) {
        this.habilitaImprimir = habilitaImprimir;
    }

    public Boolean getHabilitaExcluir() {
        return habilitaExcluir;
    }

    public void setHabilitaExcluir(Boolean habilitaExcluir) {
        this.habilitaExcluir = habilitaExcluir;
    }

    public TpStatusBoleto getStatus() {
        return status;
    }

    public void setStatus(TpStatusBoleto status) {
        this.status = status;
    }
}
