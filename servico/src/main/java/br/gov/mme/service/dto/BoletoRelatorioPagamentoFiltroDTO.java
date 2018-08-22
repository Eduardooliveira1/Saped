package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import br.gov.mme.util.SapedUtil;

public class BoletoRelatorioPagamentoFiltroDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Long> idsPessoasJuridicas;

    private BigDecimal valor;

    private Integer mesReferencia;

    private String dataVencimento;

    private String tpStatusBoleto;

    public List<Long> getIdsPessoasJuridicas() {
        return SapedUtil.instanciarLista(this.idsPessoasJuridicas);
    }

    public void setIdsPessoasJuridicas(List<Long> idPessoasJuridicas) {
        this.idsPessoasJuridicas = SapedUtil.instanciarLista(idPessoasJuridicas);
    }

    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Integer mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getTpStatusBoleto() {
        return tpStatusBoleto;
    }

    public void setTpBoleto(String tpStatusBoleto) {
        this.tpStatusBoleto = tpStatusBoleto;
    }

}
