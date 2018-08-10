package br.gov.mme.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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

    @ManyToOne(optional = true)
    @JoinColumn(name = "fk_Boleto_Adiantamento", referencedColumnName = "pk_Boleto")
    private Boleto boletoAdiantamento;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "fk_PessoaJuridica", referencedColumnName = "fk_Pessoa_Juridica")
    private PessoaJuridica pessoaJuridica;

    @Digits(integer = 2, fraction = 0)
    @Column(name = "mm_Referencia")
    private Integer mesReferencia;

    @Digits(integer = 4, fraction = 0)
    @Column(name = "aa_Referencia")
    private Integer anoReferencia;

    @Column(name = "tp_Boleto", length = 2, columnDefinition = "char(2)")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TpBoleto tpBoleto;

    @Digits(integer = 17, fraction = 0)
    @Column(name="nr_Nosso_Numero")
    private Long nossoNumero;

    @Digits(integer = 1, fraction = 0)
    @Column(name = "dv_Nosso_Numero")
    private Integer dvNossoNumero;

    @Column(name = "vl_Boleto")
    @Digits(integer = 20, fraction = 2)
    @NotNull
    private BigDecimal valorBoleto;

    @Column(name = "vl_Juros")
    @Digits(integer = 20, fraction = 2)
    private BigDecimal juros;

    @Column(name = "vl_Multa")
    @Digits(integer = 20, fraction = 2)
    private BigDecimal multa;

    @Column(name = "dt_Vencimento")
    @NotNull
    private LocalDate dataVencimento;

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

    public Integer getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Integer mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Integer getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(Integer anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public TpBoleto getTpBoleto() {
        return tpBoleto;
    }

    public void setTpBoleto(TpBoleto tpBoleto) {
        this.tpBoleto = tpBoleto;
    }

    public Long getNossoNumero() {
        return nossoNumero;
    }

    public void setNossoNumero(Long nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public Integer getDvNossoNumero() {
        return dvNossoNumero;
    }

    public void setDvNossoNumero(Integer dvNossoNumero) {
        this.dvNossoNumero = dvNossoNumero;
    }

    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    public void setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    public BigDecimal getJuros() {
        return juros;
    }

    public void setJuros(BigDecimal juros) {
        this.juros = juros;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

}
