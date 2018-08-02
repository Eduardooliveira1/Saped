package br.gov.mme.domain;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import br.gov.mme.enumeration.FlNotificacao;
import br.gov.mme.enumeration.FlStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "tb_Telefone_Representante")
public class Telefone implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pk_Telefone_Representante")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_Pessoa_Representante", referencedColumnName = "fk_Pessoa_Representante")
    private Representante pessoaRepresentante;

    @Column(name = "nr_Ddd")
    @Digits(integer=2, fraction=0)
    private BigDecimal ddd;

    @Column(name = "nr_Telefone")
    @Digits(integer=9, fraction=0)
    private BigDecimal telefone;

    @Column(name="fl_Status")
    @Enumerated(EnumType.STRING)
    private FlStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Representante getPessoaRepresentante() {
        return pessoaRepresentante;
    }

    public void setPessoaRepresentante(Representante pessoaRepresentante) {
        this.pessoaRepresentante = pessoaRepresentante;
    }

    public BigDecimal getDdd() {
        return ddd;
    }

    public void setDdd(BigDecimal ddd) {
        this.ddd = ddd;
    }

    public BigDecimal getTelefone() {
        return telefone;
    }

    public void setTelefone(BigDecimal telefone) {
        this.telefone = telefone;
    }

    public FlStatus getStatus() {
        return status;
    }

    public void setStatus(FlStatus status) {
        this.status = status;
    }
}
