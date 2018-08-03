package br.gov.mme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private String nomeAbreviadoBairro;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public Uf getUf() {
        return uf;
    }


    public Bairro setId(Long id) {
        this.id = id;
        return this;
    }

    public Bairro setUf(Uf uf) {
        this.uf = uf;
        return this;
    }

    public Long getId() {
        return id;
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

    public String getNomeAbreviadoBairro() {
        return nomeAbreviadoBairro;
    }

    public Bairro setNomeAbreviadoBairro(String nomeAbreviadoBairro) {
        this.nomeAbreviadoBairro = nomeAbreviadoBairro;
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
