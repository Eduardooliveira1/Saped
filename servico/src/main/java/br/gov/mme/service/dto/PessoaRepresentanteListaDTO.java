package br.gov.mme.service.dto;

import br.gov.mme.util.SapedUtil;

import java.util.List;

public class PessoaRepresentanteListaDTO {

    private Long id;
    private String nome;
    private String cargo;
    private String email;
    private List<TelefoneListaRepresentanteDTO> telefone;

    public PessoaRepresentanteListaDTO() {
    }

    public PessoaRepresentanteListaDTO(Long id, String nome, String cargo, String email) {
        this.id = id;
        this.cargo = cargo;
        this.email = email;
        this.nome = nome;
    }

    public List<TelefoneListaRepresentanteDTO> getTelefone() {
        return SapedUtil.instanciarLista(telefone);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(List<TelefoneListaRepresentanteDTO> telefone) {
        this.telefone = SapedUtil.instanciarLista(telefone);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
