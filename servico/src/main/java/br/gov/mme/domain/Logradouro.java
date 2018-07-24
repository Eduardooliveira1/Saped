package br.gov.mme.domain;

import br.gov.mme.enumeration.TpLocalidade;
import br.gov.mme.enumeration.TpStatusLogradouro;

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
@Table(name = "td_Logradouro")
public class Logradouro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_Logradouro")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Sigla_Uf")
    @NotNull
    private Uf uf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Localidade")
    @NotNull
    private Localidade localidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Bairro_Inicial")
    @NotNull
    private Bairro bairroInicial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Bairro_Final")
    @NotNull
    private Bairro bairroFinal;

    @Column(name = "no_Logradouro", length = 100)
    @Size(max = 100)
    @NotNull
    private String nomeLogradouro;

    @Column(name = "ds_Complemento", length = 100)
    @Size(max = 100)
    private String complemento;

    @Column(name = "nr_Cep", length = 8)
    @Size(max = 8)
    @NotNull
    private String cep;

    @Column(name = "no_Tipo_logradouro", length = 36)
    @Size(max = 36)
    private String nomeTipoLogradouro;

    @Column(name = "tp_Status_Logradouro")
    @Enumerated(EnumType.STRING)
    private TpStatusLogradouro tipoStatusLogradouro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Localidade_Subordinada")
    private Logradouro localidadeSubordinada;

    @Column(name = "no_Abreviado", length = 150)
    @Size(max = 150)
    private String nomeAbreviado;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public Long getId() {
        return id;
    }

    public Logradouro setId(Long id) {
        this.id = id;
        return this;
    }

    public Uf getUf() {
        return uf;
    }

    public Logradouro setUf(Uf uf) {
        this.uf = uf;
        return this;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public Logradouro setLocalidade(Localidade localidade) {
        this.localidade = localidade;
        return this;
    }

    public Bairro getBairroInicial() {
        return bairroInicial;
    }

    public Logradouro setBairroInicial(Bairro bairroInicial) {
        this.bairroInicial = bairroInicial;
        return this;
    }

    public Bairro getBairroFinal() {
        return bairroFinal;
    }

    public Logradouro setBairroFinal(Bairro bairroFinal) {
        this.bairroFinal = bairroFinal;
        return this;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public Logradouro setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public Logradouro setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public Logradouro setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getNomeTipoLogradouro() {
        return nomeTipoLogradouro;
    }

    public Logradouro setNomeTipoLogradouro(String nomeTipoLogradouro) {
        this.nomeTipoLogradouro = nomeTipoLogradouro;
        return this;
    }

    public TpStatusLogradouro getTipoStatusLogradouro() {
        return tipoStatusLogradouro;
    }

    public Logradouro setTipoStatusLogradouro(TpStatusLogradouro tipoStatusLogradouro) {
        this.tipoStatusLogradouro = tipoStatusLogradouro;
        return this;
    }

    public Logradouro getLocalidadeSubordinada() {
        return localidadeSubordinada;
    }

    public Logradouro setLocalidadeSubordinada(Logradouro localidadeSubordinada) {
        this.localidadeSubordinada = localidadeSubordinada;
        return this;
    }

    public String getNomeAbreviado() {
        return nomeAbreviado;
    }

    public Logradouro setNomeAbreviado(String nomeAbreviado) {
        this.nomeAbreviado = nomeAbreviado;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public Logradouro setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
