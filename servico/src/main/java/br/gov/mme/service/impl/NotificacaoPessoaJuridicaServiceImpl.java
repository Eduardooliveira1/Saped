package br.gov.mme.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.domain.NotificacaoPessoaJuridica;
import br.gov.mme.repository.NotificacaoPessoaJuridicaRepository;
import br.gov.mme.service.NotificacaoPessoaJuridicaService;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class NotificacaoPessoaJuridicaServiceImpl implements NotificacaoPessoaJuridicaService {
	
	private final NotificacaoPessoaJuridicaRepository notificacaoPessoaJuridicaRepository;
	
	public NotificacaoPessoaJuridicaServiceImpl(NotificacaoPessoaJuridicaRepository notificacaoPessoaJuridicaRepository) {
		this.notificacaoPessoaJuridicaRepository = notificacaoPessoaJuridicaRepository;
	}

	@Override
	public void salvarNotificacaoPessoaJuridica(NotificacaoPessoaJuridica notificacaoPessoaJuridica) {
		notificacaoPessoaJuridicaRepository.save(notificacaoPessoaJuridica);
	}





}
