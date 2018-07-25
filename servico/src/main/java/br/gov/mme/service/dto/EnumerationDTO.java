package br.gov.mme.service.dto;

import java.io.Serializable;

public class EnumerationDTO implements Serializable {

    private String id;

    private String descricao;

    public String getId() {
        return id;
    }

    public EnumerationDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public EnumerationDTO setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
}
