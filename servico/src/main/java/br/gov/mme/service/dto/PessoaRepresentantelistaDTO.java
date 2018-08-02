package br.gov.mme.service.dto;
import br.gov.mme.domain.Pessoa;
import br.gov.mme.enumeration.FlNotificacao;
import java.util.List;

import java.math.BigDecimal;

public class PessoaRepresentantelistaDTO {

    private Long id;
    private String nome;
    private String cargo;
    private FlNotificacao notificacao;
    private String email;
    private List<TelefoneDTO> telefone;

    public PessoaRepresentantelistaDTO() {
    }

    public PessoaRepresentantelistaDTO(Long id,
                                       String nome,
                                       String cargo,
                                       FlNotificacao notificacao,
                                       String email,
                                       List<TelefoneDTO> telefone) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.notificacao = notificacao;
        this.email = email;
        this.telefone = telefone;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public FlNotificacao getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(FlNotificacao notificacao) {
        this.notificacao = notificacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TelefoneDTO> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<TelefoneDTO> telefone) {
        this.telefone = telefone;
    }
}
