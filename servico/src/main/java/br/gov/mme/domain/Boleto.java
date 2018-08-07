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
        return mesReferencia;
    }

    public void setMmReferencia(BigInteger mmReferencia) {
        this.mesReferencia = mmReferencia;
    }

    public BigInteger getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(BigInteger anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public TpBoleto getTpBoleto() {
        return tpBoleto;
    }

    public void setTpBoleto(TpBoleto tpBoleto) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Boleto other = (Boleto) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
