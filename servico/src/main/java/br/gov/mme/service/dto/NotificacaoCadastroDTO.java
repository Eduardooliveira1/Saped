package br.gov.mme.service.dto;

public class NotificacaoCadastroDTO {

    private Long id;
    private String assunto;
    private String conteudo;
    
    public NotificacaoCadastroDTO(Long id, String assunto, String conteudo) {
        this.id = id;
        this.assunto = assunto;
        this.conteudo = conteudo;

    }

    public NotificacaoCadastroDTO() {
    }



    public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Long getId() {
        return id;
    }

    public NotificacaoCadastroDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
