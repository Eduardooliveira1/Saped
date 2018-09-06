package br.gov.mme.service.dto;

import java.io.Serializable;

public class PessoaJuridicaNomeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String cnpj;

    private String nomeFantasia;

    public PessoaJuridicaNomeDTO(Long id, String cnpj, String nomeFantasia) {
        this.id = id;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
    }

    public PessoaJuridicaNomeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

}
