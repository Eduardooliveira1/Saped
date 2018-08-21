package br.gov.mme.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ta_Notificacao_Pessoa_Juridica")
public class NotificacaoPessoaJuridica implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    NotificacaoPessoaJuridicaId notificacaoPessoaJuridicaId;

	public NotificacaoPessoaJuridicaId getNotificacaoPessoaJuridicaId() {
		return notificacaoPessoaJuridicaId;
	}

	public void setNotificacaoPessoaJuridicaId(NotificacaoPessoaJuridicaId notificacaoPessoaJuridicaId) {
		this.notificacaoPessoaJuridicaId = notificacaoPessoaJuridicaId;
	}
    
    
   
}
