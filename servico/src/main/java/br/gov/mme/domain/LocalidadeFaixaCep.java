package br.gov.mme.domain;


import br.gov.mme.enumeration.TpFaixaCep;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "td_Localidade_Faixa_Cep")
public class LocalidadeFaixaCep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_Localidade", referencedColumnName = "pk_Localidade")
    private Localidade localidade;

    @Size(max = 8)
    @Column(name="nr_Cep_Inicial", length = 8)
    private String cepInicial;

    @Size(max = 8)
    @Column(name="nr_Cep_Final", length = 8)
    private String cepFinal;

    @Column(name = "tp_Faixa")
    @Enumerated(EnumType.STRING)
    private TpFaixaCep tipoFaixa;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public Long getId() {
        return id;
    }

    public LocalidadeFaixaCep setId(Long id) {
        this.id = id;
        return this;
    }

    public Localidade getLocalidade() {
        return localidade;
    }

    public LocalidadeFaixaCep setLocalidade(Localidade localidade) {
        this.localidade = localidade;
        return this;
    }

    public String getCepInicial() {
        return cepInicial;
    }

    public LocalidadeFaixaCep setCepInicial(String cepInicial) {
        this.cepInicial = cepInicial;
        return this;
    }

    public String getCepFinal() {
        return cepFinal;
    }

    public LocalidadeFaixaCep setCepFinal(String cepFinal) {
        this.cepFinal = cepFinal;
        return this;
    }

    public TpFaixaCep getTipoFaixa() {
        return tipoFaixa;
    }

    public LocalidadeFaixaCep setTipoFaixa(TpFaixaCep tipoFaixa) {
        this.tipoFaixa = tipoFaixa;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public LocalidadeFaixaCep setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
