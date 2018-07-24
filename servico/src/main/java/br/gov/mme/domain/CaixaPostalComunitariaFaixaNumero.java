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
@Table(name = "td_Caixa_Postal_Comunitaria_Faixa_Numero")
public class CaixaPostalComunitariaFaixaNumero implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_Caixa_Postal_Comunitaria", referencedColumnName = "fk_Caixa_Postal_Comunitaria")
    private CaixaPostalComunitaria caixaPostalComunitaria;

    @Column(name = "nr_Inicial", length = 6)
    @Size(max = 6)
    private String nrIicial;

    @Column(name = "nr_Inicial", length = 6)
    @Size(max = 6)
    private String nrFinal;

    @NotNull
    @Column(name = "nr_Versao_Dne")
    private Integer versaoDne;

    public Long getId() {
        return id;
    }

    public CaixaPostalComunitariaFaixaNumero setId(Long id) {
        this.id = id;
        return this;
    }

    public CaixaPostalComunitaria getCaixaPostalComunitaria() {
        return caixaPostalComunitaria;
    }

    public CaixaPostalComunitariaFaixaNumero setCaixaPostalComunitaria(CaixaPostalComunitaria caixaPostalComunitaria) {
        this.caixaPostalComunitaria = caixaPostalComunitaria;
        return this;
    }

    public String getNrIicial() {
        return nrIicial;
    }

    public CaixaPostalComunitariaFaixaNumero setNrIicial(String nrIicial) {
        this.nrIicial = nrIicial;
        return this;
    }

    public String getNrFinal() {
        return nrFinal;
    }

    public CaixaPostalComunitariaFaixaNumero setNrFinal(String nrFinal) {
        this.nrFinal = nrFinal;
        return this;
    }

    public Integer getVersaoDne() {
        return versaoDne;
    }

    public CaixaPostalComunitariaFaixaNumero setVersaoDne(Integer versaoDne) {
        this.versaoDne = versaoDne;
        return this;
    }
}
