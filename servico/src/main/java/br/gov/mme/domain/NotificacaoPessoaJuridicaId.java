package br.gov.mme.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class NotificacaoPessoaJuridicaId implements Serializable  {
	
    private static final long serialVersionUID = 1L;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_Notificacao", referencedColumnName = "pk_Notificacao")
    private Notificacao notificacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_Pessoa_Juridica", referencedColumnName = "fk_Pessoa_Juridica")
    private PessoaJuridica pessoaJuridica;

	public Notificacao getNotificacao() {
		return notificacao;
	}

	public void setNotificacao(Notificacao notificacao) {
		this.notificacao = notificacao;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
    
}
