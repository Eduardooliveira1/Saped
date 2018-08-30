package br.gov.mme.service.dto;

public class ComunicacaoRepresentantelistaDTO {

    private Long idPessoa;
    private Long idPessoaJuridica;
    private String email;
    private String nome;
    private String nomeFantasia;

    public ComunicacaoRepresentantelistaDTO() {
    }

    public ComunicacaoRepresentantelistaDTO(Long idPessoa,Long idPessoaJuridica,String email,String nome, String nomeFantasia) {
        this.idPessoa = idPessoa;
        this.idPessoaJuridica = idPessoaJuridica;
        this.email = email;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
    }

    public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Long getIdPessoaJuridica() {
		return idPessoaJuridica;
	}

	public void setIdPessoaJuridica(Long idPessoaJuridica) {
		this.idPessoaJuridica = idPessoaJuridica;
	}
}