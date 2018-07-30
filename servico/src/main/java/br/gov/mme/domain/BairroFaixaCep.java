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
@Table(name = "td_Bairro_Faixa_Cep")
public class BairroFaixaCep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_Bairro", referencedColumnName = "pk_Bairro")
    private Bairro bairro;

    @Size(max = 8)
    @Column(name="nr_Cep_Inicial", length = 8)
    private String cepInicial;

    @Size(max = 8)
    @Column(name="nr_Cep_Final", length = 8)
    private String cepFinal;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public Long getId() {
        return id;
    }

    public BairroFaixaCep setId(Long id) {
        this.id = id;
        return this;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public BairroFaixaCep setBairro(Bairro bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCepInicial() {
        return cepInicial;
    }

    public BairroFaixaCep setCepInicial(String cepInicial) {
        this.cepInicial = cepInicial;
        return this;
    }

    public String getCepFinal() {
        return cepFinal;
    }

    public BairroFaixaCep setCepFinal(String cepFinal) {
        this.cepFinal = cepFinal;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public BairroFaixaCep setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
