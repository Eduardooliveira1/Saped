package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import br.gov.mme.enumeration.TpBoleto;
import br.gov.mme.util.SapedUtil;

public class BoletoRelatorioPagamentoFiltroDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Set<Long> idsPessoasJuridicas;

    private BigDecimal valor;

    private BigInteger mesReferencia;

    private LocalDate dataVencimento;

    private TpBoleto tpBoleto;

    public Set<Long> getIdsPessoasJuridicas() {
        return SapedUtil.instanciarSet(this.idsPessoasJuridicas);
    }

    public void setIdsPessoasJuridicas(Set<Long> idPessoasJuridicas) {
        this.idsPessoasJuridicas = SapedUtil.instanciarSet(idPessoasJuridicas);
    }

    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigInteger getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(BigInteger mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public TpBoleto getTpBoleto() {
        return tpBoleto;
    }

    public void setTpBoleto(TpBoleto tpBoleto) {
        this.tpBoleto = tpBoleto;
    }

}
