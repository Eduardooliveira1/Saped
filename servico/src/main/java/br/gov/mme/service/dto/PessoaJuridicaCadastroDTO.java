package br.gov.mme.service.dto;

public class PessoaJuridicaCadastroDTO {

    private Long id;
    private String cnpj;
    private String sigla;
    private String nomeFantasia;
    private String razaoSocial;

    public PessoaJuridicaCadastroDTO(Long id, String cnpj, String sigla, String nomeFantasia, String razaoSocial) {
        this.id = id;
        this.cnpj = cnpj;
        this.sigla = sigla;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public PessoaJuridicaCadastroDTO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public PessoaJuridicaCadastroDTO setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getSigla() {
        return sigla;
    }

    public PessoaJuridicaCadastroDTO setSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public PessoaJuridicaCadastroDTO setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
        return this;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public PessoaJuridicaCadastroDTO setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public Long getId() {
        return id;
    }

    public PessoaJuridicaCadastroDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
