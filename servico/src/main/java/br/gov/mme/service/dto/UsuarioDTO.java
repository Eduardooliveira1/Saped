package br.gov.mme.service.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public UsuarioDTO setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public String getSenha() {
        return senha;
    }

    public UsuarioDTO setSenha(String senha) {
        this.senha = senha;
        return this;
    }
}
