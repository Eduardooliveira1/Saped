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
@Table(name = "td_Caixa_Postal_Comunitaria")
public class CaixaPostalComunitaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_Caixa_Postal_Comunitaria")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Sigla_Uf")
    @NotNull
    private Uf uf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_Localidade")
    @NotNull
    private Localidade localidade;

    @Column(name = "no_Caixa_Postal_Comunitaria", length = 72)
    @Size(max = 72)
    private String nomeCaixaPostalComunitaria;

    @Column(name = "ed_endereco", length = 300)
    @Size(max = 300)
    private String endereco;

    @Column(name = "nr_Cep", length = 8)
    @Size(max = 8)
    private String cep;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;


    public CaixaPostalComunitaria setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Uf getUf() {
        return uf;
    }

    public CaixaPostalComunitaria setUf(Uf uf) {
        this.uf = uf;
        return this;
    }


    public CaixaPostalComunitaria setLocalidade(Localidade localidade) {
        this.localidade = localidade;
        return this;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public String getNomeCaixaPostalComunitaria() {
        return nomeCaixaPostalComunitaria;
    }

    public CaixaPostalComunitaria setNomeCaixaPostalComunitaria(String nomeCaixaPostalComunitaria) {
        this.nomeCaixaPostalComunitaria = nomeCaixaPostalComunitaria;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public CaixaPostalComunitaria setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public String getCep() {
        return cep;
    }

    public CaixaPostalComunitaria setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public CaixaPostalComunitaria setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
