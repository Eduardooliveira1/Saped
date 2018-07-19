package br.gov.mme.service.dto;

public class PessoaJuridicaListaDTO {

    private String cnpj;
    private String sigla;
    private String nomeFantasia;
    private String razaoSocial;

    public PessoaJuridicaListaDTO(String cnpj, String sigla, String nomeFantasia, String razaoSocial) {
        this.cnpj = cnpj;
        this.sigla = sigla;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public PessoaJuridicaListaDTO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public PessoaJuridicaListaDTO setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getSigla() {
        return sigla;
    }

    public PessoaJuridicaListaDTO setSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public PessoaJuridicaListaDTO setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
        return this;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public PessoaJuridicaListaDTO setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }
}
