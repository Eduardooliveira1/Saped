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
@Table(name = "td_Logradouro_Denominacao")
public class LogradouroDenominacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_Logradouro", referencedColumnName = "fk_Logradouro")
    private Logradouro logradouro;

    @Column(name="nr_ordem")
    private Integer ordem;

    @Size(max = 36)
    @Column(name="no_Tipo_Logradouro", length = 36)
    private String nomeTipoLogradouro;

    @Size(max = 150)
    @Column(name="no_Denominacao", length = 150)
    private String nomeDenominacao;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public Long getId() {
        return id;
    }

    public LogradouroDenominacao setId(Long id) {
        this.id = id;
        return this;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public LogradouroDenominacao setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public LogradouroDenominacao setOrdem(Integer ordem) {
        this.ordem = ordem;
        return this;
    }

    public String getNomeTipoLogradouro() {
        return nomeTipoLogradouro;
    }

    public LogradouroDenominacao setNomeTipoLogradouro(String nomeTipoLogradouro) {
        this.nomeTipoLogradouro = nomeTipoLogradouro;
        return this;
    }

    public String getNomeDenominacao() {
        return nomeDenominacao;
    }

    public LogradouroDenominacao setNomeDenominacao(String nomeDenominacao) {
        this.nomeDenominacao = nomeDenominacao;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public LogradouroDenominacao setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
