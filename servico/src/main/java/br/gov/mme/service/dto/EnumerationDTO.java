package br.gov.mme.service.dto;

import java.io.Serializable;

public class EnumerationDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String descricao;

    public String getId() {
        return id;
    }

    public EnumerationDTO() {
    }

    public EnumerationDTO(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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
