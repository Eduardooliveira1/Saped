package br.gov.mme.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "td_Bairro_Denominacao")
public class BairroDenominacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_Bairro", referencedColumnName = "pk_Bairro")
    private Bairro bairro;

    @Column(name="nr_ordem")
    private Integer ordem;

    @Size(max = 72)
    @Column(name="no_Denominacao", length = 72)
    private String nomeDenominacao;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public Long getId() {
        return id;
    }

    public BairroDenominacao setId(Long id) {
        this.id = id;
        return this;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public BairroDenominacao setBairro(Bairro bairro) {
        this.bairro = bairro;
        return this;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public BairroDenominacao setOrdem(Integer ordem) {
        this.ordem = ordem;
        return this;
    }

    public String getNomeDenominacao() {
        return nomeDenominacao;
    }

    public BairroDenominacao setNomeDenominacao(String nomeDenominacao) {
        this.nomeDenominacao = nomeDenominacao;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public BairroDenominacao setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
