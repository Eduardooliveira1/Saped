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
@Table(name = "td_Localidade_Denominacao")
public class LocalidadeDenominacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_Localidade", referencedColumnName = "fk_Localidade")
    private Localidade localidade;

    @Column(name = "nr_ordem")
    private Integer ordem;

    @Size(max = 72)
    @Column(name = "no_Denominacao", length = 72)
    private String nomeDenominacaoLocalidade;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public LocalidadeDenominacao setId(Long id) {
        this.id = id;
        return this;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public Long getId() {
        return id;
    }

    public LocalidadeDenominacao setLocalidade(Localidade localidade) {
        this.localidade = localidade;
        return this;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public LocalidadeDenominacao setOrdem(Integer ordem) {
        this.ordem = ordem;
        return this;
    }

    public String getNomeDenominacaoLocalidade() {
        return nomeDenominacaoLocalidade;
    }

    public LocalidadeDenominacao setNomeDenominacaoLocalidade(String nomeDenominacaoLocalidade) {
        this.nomeDenominacaoLocalidade = nomeDenominacaoLocalidade;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public LocalidadeDenominacao setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
