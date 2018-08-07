package br.gov.mme.domain;


import br.gov.mme.enumeration.FlNotificacao;
import br.gov.mme.util.SapedUtil;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "tb_Pessoa_Representante")
public class Representante implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @MapsId
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Pessoa_Representante", referencedColumnName = "pk_Pessoa")
    private Pessoa pessoa;

    @OneToOne(optional = false)
    @JoinColumn(name = "fk_Pessoa_Juridica", referencedColumnName = "fk_Pessoa_Juridica")
    private PessoaJuridica pessoaJuridica;

    @NotNull
    @Size(max = 100)
    @Column(name="no_Representante", length = 100)
    private String nome;

    @NotNull
    @Size(max = 80)
    @Column(name="no_Cargo", length = 80)
    private String cargo;

    @Column(name="fl_Notificacao")
    @Enumerated(EnumType.STRING)
    private FlNotificacao notificacao;

    @OneToMany(mappedBy = "pessoaRepresentante",cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Telefone> telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    @NotNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    @NotNull
    public String getCargo() {
        return cargo;
    }

    public void setCargo(@NotNull String cargo) {
        this.cargo = cargo;
    }

    public FlNotificacao getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(FlNotificacao notificacao) {
        this.notificacao = notificacao;
    }

    public List<Telefone> getTelefone() {
        return SapedUtil.instanciarLista(telefone);
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = SapedUtil.instanciarLista(telefone);
    }
}
