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
@Table(name = "tb_Pessoa_Juridica")
public class PessoaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_Pessoa_Juridica", referencedColumnName = "pk_Pessoa")
    private Pessoa pessoa;

    @NotNull
    @Size(max = 14)
    @Column(name="co_Cnpj", length = 14)
    private String cnpj;

    @NotNull
    @Size(max = 100)
    @Column(name="no_Fantasia", length = 100)
    private String nomeFantasia;

    @NotNull
    @Size(max = 100)
    @Column(name="no_Razao_Social", length = 100)
    private String razaoSocial;

    @NotNull
    @Size(max = 20)
    @Column(name="sg_Pessoa_Juridica", length = 20)
    private String sigla;

    @Size(max = 300)
    @Column(name="ds_Senha_Acesso", length = 300)
    private String senhaAcesso;

    public Long getId() {
        return id;
    }

    public PessoaJuridica setId(Long id) {
        this.id = id;
        return this;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public PessoaJuridica setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public PessoaJuridica setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public PessoaJuridica setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
        return this;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public PessoaJuridica setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public String getSigla() {
        return sigla;
    }

    public PessoaJuridica setSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public PessoaJuridica setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
        return this;
    }
}
