package br.gov.mme.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import br.gov.mme.enumeration.TpBoleto;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;

@Entity
@Table(name = "tb_Boleto")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
@SqlResultSetMappings(value = {
    @SqlResultSetMapping(name = "BoletoRelatorioPagamentoDTO",
        classes =
            {@ConstructorResult(targetClass = BoletoRelatorioPagamentoDTO.class, 
                columns = { @ColumnResult(name = "cnpj"),
                            @ColumnResult(name = "nomeFantasia"),
                            @ColumnResult(name = "valorBoleto"),
                            @ColumnResult(name = "mesReferencia", type = Integer.class),
                            @ColumnResult(name = "anoReferencia", type = Integer.class),
                            @ColumnResult(name = "dataVencimento", type = LocalDate.class),
                            @ColumnResult(name = "statusBoleto", type = String.class)
                }
            )}
    )}
)
public class Boleto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_Boleto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Boleto_Adiantamento")
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
    @NotNull
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
