package br.gov.mme.service.dto;

import java.io.Serializable;

public class PessoaJuridicaListaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cnpj;
    private String sigla;
    private String nomeFantasia;
    private String razaoSocial;
	private Long id;

	public PessoaJuridicaListaDTO() {
		this(null, null, null, null, null);
	}

	public PessoaJuridicaListaDTO(String cnpj, String sigla, String nomeFantasia, String razaoSocial) {
		this(cnpj, sigla, nomeFantasia, razaoSocial, null);
	}

	public PessoaJuridicaListaDTO(String cnpj, String sigla, String nomeFantasia, String razaoSocial,
			Long id) {
		this.cnpj = cnpj;
		this.sigla = sigla;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.id = id;
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

	public void setId(Long id) {
		this.id = id;
	}

}
