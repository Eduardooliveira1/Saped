package br.gov.mme.service.dto;
import java.util.List;

public class PessoaJuridicaCadastroDTO {

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
        this.representantes = representantes;
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
        return representantes;
    }

    public void setRepresentantes(List<PessoaRepresentanteCadastroDTO> representantes) {
        this.representantes = representantes;
    }
}
