package br.gov.mme.service.dto;

import java.io.Serializable;

public class CredenciaisNovaSenhaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String cnpj;
    
    private String novaSenha;

    public CredenciaisNovaSenhaDTO(String cnpj, String novaSenha) {
        this.cnpj = cnpj;
        this.novaSenha = novaSenha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

}
