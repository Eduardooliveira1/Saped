package br.gov.mme.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import br.gov.mme.enumeration.TpBoleto;

@Entity
@Table(name = "tb_Boleto")
public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_Boleto")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_Boleto_Adiantamento", referencedColumnName = "pk_Boleto")
    @NotNull
    private Boleto boletoAdiantamento;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "fk_PessoaJuridica", referencedColumnName = "fk_Pessoa_Juridica")
    private PessoaJuridica pessoaJuridica;

    @Digits(integer = 2, fraction = 0)
    @Column(name = "mm_Referencia")
    @NotNull
    private BigInteger mesReferencia;

    @Digits(integer = 4, fraction = 0)
    @Column(name = "aa_Referencia")
    @NotNull
    private BigInteger anoReferencia;

    @Column(name = "tp_Boleto", length = 2, columnDefinition = "char(2)")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TpBoleto tpBoleto;

    @Digits(integer = 17, fraction = 0)
    @Column(name="nr_Nosso_Numero")
    @NotNull
    private BigInteger nossoNumero;

    @Column(name = "vlBoleto")
    @Digits(integer = 20, fraction = 2)
    @NotNull
    private BigDecimal valorBoleto;

    @Column(name = "dt_Vencimento")
    @NotNull
    private LocalDate dataVencimento;

    public Long getId() {
        return id;
    }

    public Boleto setId(Long id) {
        this.id = id;
        return this;
    }

    public Boleto getBoletoAdiantamento() {
        return boletoAdiantamento;
    }

    public Boleto setBoletoAdiantamento(Boleto boletoAdiantamento) {
        this.boletoAdiantamento = boletoAdiantamento;
        return this;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public Boleto setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
        return this;
    }

    public BigInteger getMesReferencia() {
        return mesReferencia;
    }

    public Boleto setMesReferencia(BigInteger mesReferencia) {
        this.mesReferencia = mesReferencia;
        return this;
    }

    public BigInteger getAnoReferencia() {
        return anoReferencia;
    }

    public Boleto setAnoReferencia(BigInteger anoReferencia) {
        this.anoReferencia = anoReferencia;
        return this;
    }

    public TpBoleto getTpBoleto() {
        return tpBoleto;
    }

    public Boleto setTpBoleto(TpBoleto tpBoleto) {
        this.tpBoleto = tpBoleto;
        return this;
    }

    public BigInteger getNossoNumero() {
        return nossoNumero;
    }

    public Boleto setNossoNumero(BigInteger nossoNumero) {
        this.nossoNumero = nossoNumero;
        return this;
    }

    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public Boleto setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
        return this;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public Boleto setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
        return this;
    }

}
