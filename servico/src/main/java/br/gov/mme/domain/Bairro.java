package br.gov.mme.domain;

import br.gov.mme.enumeration.FlStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "td_Bairro")
public class Bairro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_Bairro")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Sigla_Uf")
    @NotNull
    private Uf uf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Localidade")
    @NotNull
    private Localidade localidade;

    @Column(name = "no_bairro", length = 72)
    @Size(max = 72)
    @NotNull
    private String nomeBairro;

    @Column(name = "no_Abreviado", length = 36)
    @Size(max = 36)
    private String nomeAbreviado;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public Long getId() {
        return id;
    }

    public Bairro setId(Long id) {
        this.id = id;
        return this;
    }

    public Uf getUf() {
        return uf;
    }

    public Bairro setUf(Uf uf) {
        this.uf = uf;
        return this;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public Bairro setLocalidade(Localidade localidade) {
        this.localidade = localidade;
        return this;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public Bairro setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
        return this;
    }

    public String getNomeAbreviado() {
        return nomeAbreviado;
    }

    public Bairro setNomeAbreviado(String nomeAbreviado) {
        this.nomeAbreviado = nomeAbreviado;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public Bairro setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
