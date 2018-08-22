package br.gov.mme.service.dto;

import br.gov.mme.enumeration.AcaoGerarBoleto;
import br.gov.mme.enumeration.TpStatusBoleto;

import java.io.Serializable;

public class cobrancaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String mesReferencia;
    private String dataPagamento;
    private String dataSegundaVia;
    private String valor;
    private AcaoGerarBoleto acao;
    private Boolean habilitaImprimir;
    private Boolean habilitaExcluir;
    private TpStatusBoleto status;


    public cobrancaDTO() { }

    public cobrancaDTO(String mesReferencia, String dataPagamento, String dataSegundaVia, String valor, AcaoGerarBoleto acao, Boolean habilitaImprimir, Boolean habilitaExcluir, TpStatusBoleto status) {
        this.mesReferencia = mesReferencia;
        this.dataPagamento = dataPagamento;
        this.dataSegundaVia = dataSegundaVia;
        this.valor = valor;
        this.acao = acao;
        this.habilitaImprimir = habilitaImprimir;
        this.habilitaExcluir = habilitaExcluir;
        this.status = status;
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

    public AcaoGerarBoleto getAcao() {
        return acao;
    }

    public void setAcao(AcaoGerarBoleto acao) {
        this.acao = acao;
    }

    public Boolean getHabilitaImprimir() {
        return habilitaImprimir;
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
