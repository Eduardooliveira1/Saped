package br.gov.mme.domain;


import br.gov.mme.enumerator.FlStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_Pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="pk_Pessoa")
    private Long id;

    @Size(max = 70)
    @Column(name="em_Pessoa", length = 70)
    private String email;

    @Column(name="fl_Status")
    @Enumerated(EnumType.STRING)
    private FlStatus status;

    @NotNull
    @Column(name = "dt_Cadastro")
    private LocalDateTime dataCadastro;

    public Long getId() {
        return id;
    }

    public Pessoa setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Pessoa setEmail(String email) {
        this.email = email;
        return this;
    }

    public FlStatus getStatus() {
        return status;
    }

    public Pessoa setStatus(FlStatus status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Pessoa setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }
}
