package br.gov.mme.service.dto;

import java.io.Serializable;

public class RepresentanteEMailECNPJDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cnpj;

    private String email;

    public RepresentanteEMailECNPJDTO(String cnpj, String email) {
        this.cnpj = cnpj;
        this.email = email;
    }

    public RepresentanteEMailECNPJDTO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
