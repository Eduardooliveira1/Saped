package br.gov.mme.service.dto;
import br.gov.mme.util.SapedUtil;

import java.util.List;

import java.io.Serializable;

public class PessoaJuridicaCadastroDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String cnpj;
    private String sigla;
    private String nomeFantasia;
    private String razaoSocial;
    private List<PessoaRepresentanteCadastroDTO> representantes;

    public PessoaJuridicaCadastroDTO(Long id, String cnpj, String sigla, String nomeFantasia, String razaoSocial) {
        this.id = id;
        this.cnpj = cnpj;
        this.sigla = sigla;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public PessoaJuridicaCadastroDTO(Long id, String cnpj, String sigla, String nomeFantasia, String razaoSocial, List<PessoaRepresentanteCadastroDTO> representantes) {
        this.id = id;
        this.cnpj = cnpj;
        this.sigla = sigla;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.representantes =  SapedUtil.instanciarLista(representantes);
    }

    public PessoaJuridicaCadastroDTO() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public PessoaJuridicaCadastroDTO setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getSigla() {
        return sigla;
    }

    public PessoaJuridicaCadastroDTO setSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public PessoaJuridicaCadastroDTO setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
        return this;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public PessoaJuridicaCadastroDTO setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
        return this;
    }

    public Long getId() {
        return id;
    }

    public PessoaJuridicaCadastroDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public List<PessoaRepresentanteCadastroDTO> getRepresentantes() {
        return SapedUtil.instanciarLista(representantes);
    }

    public void setRepresentantes(List<PessoaRepresentanteCadastroDTO> representantes) {

        this.representantes = SapedUtil.instanciarLista(representantes);
    }
}
