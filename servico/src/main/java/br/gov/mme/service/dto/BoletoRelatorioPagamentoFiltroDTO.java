package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.util.SapedUtil;

public class BoletoRelatorioPagamentoFiltroDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Long> idsPessoasJuridicas;

    private BigDecimal valor;

    private Integer mesReferencia;

    private LocalDate dataVencimento;

    private TpStatusBoleto tpStatusBoleto;

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

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public TpStatusBoleto getTpStatusBoleto() {
        return tpStatusBoleto;
    }

    public void setTpBoleto(TpStatusBoleto tpStatusBoleto) {
        this.tpStatusBoleto = tpStatusBoleto;
    }

}
