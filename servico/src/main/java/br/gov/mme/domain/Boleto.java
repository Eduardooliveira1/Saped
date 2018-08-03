package br.gov.mme.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.gov.mme.enumeration.tpBoleto;

@Entity
@Table(name = "tb_Boleto")
public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_Boleto")
    private Long id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_Boleto_Adiantamento", referencedColumnName = "pk_Boleto")
    private Boleto boletoAdiantamento;

    @MapsId
    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(name = "fk_PessoaJuridica", referencedColumnName = "fk_Pessoa_Juridica")
    private PessoaJuridica pessoaJuridica;

    @Digits(integer = 2, fraction = 0)
    @Column(name = "mm_Referencia")
    @NotNull
    private BigInteger mmReferencia;

    @Digits(integer = 4, fraction = 0)
    @Column(name = "aa_Referencia")
    @NotNull
    private BigInteger anoReferencia;

    @Column(name = "tp_Boleto", length = 2, columnDefinition = "char(2)")
    @Enumerated(EnumType.STRING)
    @NotNull
    private tpBoleto tpBoleto;

    @Digits(integer = 17, fraction = 0)
    @Column(name="nr_Nosso_Numero")
    @NotNull
    private BigInteger nossoNumero;

    @Column(name = "vlBoleto")
    @Digits(integer = 20, fraction = 2)
    @NotNull
    private BigDecimal valorBoleto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boleto getBoletoAdiantamento() {
        return boletoAdiantamento;
    }

    public void setBoletoAdiantamento(Boleto boletoAdiantamento) {
        this.boletoAdiantamento = boletoAdiantamento;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public BigInteger getMmReferencia() {
        return mmReferencia;
    }

    public void setMmReferencia(BigInteger mmReferencia) {
        this.mmReferencia = mmReferencia;
    }

    public BigInteger getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(BigInteger anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public tpBoleto getTpBoleto() {
        return tpBoleto;
    }

    public void setTpBoleto(tpBoleto tpBoleto) {
        this.tpBoleto = tpBoleto;
    }

    public BigInteger getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(BigInteger nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public void setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

}
