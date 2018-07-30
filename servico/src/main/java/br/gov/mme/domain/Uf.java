package br.gov.mme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "td_Uf")
public class Uf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk_Sigla_Uf", length = 2)
    @Size(max = 2)
    private String sigla;

    @Column(name = "nr_Cep_Inicial", length = 8)
    @Size(max = 8)
    private String cepInicial;

    @Column(name = "nr_Cep_Final", length = 8)
    @Size(max = 8)
    private String cepFinal;

    @Column(name = "nr_Versao_Dne", length = 8)
    private Integer versaoDne;

    public String getSigla() {
        return sigla;
    }

    public Uf setSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public String getCepInicial() {
        return cepInicial;
    }

    public Uf setCepInicial(String cepInicial) {
        this.cepInicial = cepInicial;
        return this;
    }

    public String getCepFinal() {
        return cepFinal;
    }

    public Uf setCepFinal(String cepFinal) {
        this.cepFinal = cepFinal;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public Uf setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
