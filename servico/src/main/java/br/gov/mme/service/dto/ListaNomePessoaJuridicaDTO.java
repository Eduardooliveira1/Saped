package br.gov.mme.service.dto;

import java.io.Serializable;

public class ListaNomePessoaJuridicaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    private String cnpj;

    private String nomeFantasia;

    public ListaNomePessoaJuridicaDTO(String cnpj, String nomeFantasia, Long id) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.id = id;
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
