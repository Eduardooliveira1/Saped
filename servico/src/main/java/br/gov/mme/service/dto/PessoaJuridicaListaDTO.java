package br.gov.mme.service.dto;

import java.io.Serializable;

public class PessoaJuridicaListaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String cnpj;
    private String sigla;
    private String nomeFantasia;
    private String razaoSocial;

    public PessoaJuridicaListaDTO(String cnpj, String sigla, String nomeFantasia, String razaoSocial) {
        this(null, cnpj, sigla, nomeFantasia, razaoSocial);
    }

    public PessoaJuridicaListaDTO(Long id, String cnpj, String sigla, String nomeFantasia, String razaoSocial) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public PessoaJuridicaListaDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
