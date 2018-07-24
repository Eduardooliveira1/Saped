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
@Table(name = "td_Localidade")
public class Localidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_Localidade")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Sigla_Uf")
    @NotNull
    private Uf uf;

    @Column(name = "no_localidade", length = 72)
    @Size(max = 72)
    @NotNull
    private String nomeLocalidade;

    @Column(name = "nr_cep", length = 8)
    @Size(max = 8)
    @NotNull
    private String cep;

    @Column(name = "fl_Localidade")
    private Integer flLocalidade;

    @Column(name = "tp_localidade")
    @Enumerated(EnumType.STRING)
    private FlStatus tipoLocalidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Localidade_Subordinada")
    private Localidade localidadeSubordinada;

    @Column(name = "no_Abreviado", length = 36)
    @Size(max = 36)
    private String nomeAbreviado;

    @Column(name = "co_Ibge", length = 7)
    @Size(max = 7)
    private String codigoIbge;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public Long getId() {
        return id;
    }

    public Localidade setId(Long id) {
        this.id = id;
        return this;
    }

    public Uf getUf() {
        return uf;
    }

    public Localidade setUf(Uf uf) {
        this.uf = uf;
        return this;
    }

    public String getNomeLocalidade() {
        return nomeLocalidade;
    }

    public Localidade setNomeLocalidade(String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Localidade setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public Integer getFlLocalidade() {
        return flLocalidade;
    }

    public Localidade setFlLocalidade(Integer flLocalidade) {
        this.flLocalidade = flLocalidade;
        return this;
    }

    public FlStatus getTipoLocalidade() {
        return tipoLocalidade;
    }

    public Localidade setTipoLocalidade(FlStatus tipoLocalidade) {
        this.tipoLocalidade = tipoLocalidade;
        return this;
    }

    public Localidade getLocalidadeSubordinada() {
        return localidadeSubordinada;
    }

    public Localidade setLocalidadeSubordinada(Localidade localidadeSubordinada) {
        this.localidadeSubordinada = localidadeSubordinada;
        return this;
    }

    public String getNomeAbreviado() {
        return nomeAbreviado;
    }

    public Localidade setNomeAbreviado(String nomeAbreviado) {
        this.nomeAbreviado = nomeAbreviado;
        return this;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public Localidade setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public Localidade setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
