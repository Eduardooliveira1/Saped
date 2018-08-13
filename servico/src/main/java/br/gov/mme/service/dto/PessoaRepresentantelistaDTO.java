package br.gov.mme.service.dto;

import br.gov.mme.enumeration.FlNotificacao;
import br.gov.mme.util.SapedUtil;

import java.util.List;

public class PessoaRepresentantelistaDTO {

    private Long id;
    private String nome;
    private String cargo;
    private String email;
    private List<TelefoneDTO> telefone;

    public PessoaRepresentantelistaDTO() {
    }

    public PessoaRepresentantelistaDTO(Long id,String nome,String cargo,String email, List<TelefoneDTO> telefone) {
        this.id = id;
        this.cargo = cargo;
        this.email = email;
        this.telefone = SapedUtil.instanciarLista(telefone);
        this.nome = nome;
    }

    public List<TelefoneDTO> getTelefone() {
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

    public void setTelefone(List<TelefoneDTO> telefone) {
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
