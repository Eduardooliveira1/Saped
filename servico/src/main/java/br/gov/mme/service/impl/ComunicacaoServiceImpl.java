package br.gov.mme.service.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.domain.Notificacao;
import br.gov.mme.domain.NotificacaoPessoaJuridica;
import br.gov.mme.domain.NotificacaoPessoaJuridicaId;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.repository.ComunicacaoRepository;
import br.gov.mme.repository.NotificacaoPessoaJuridicaRepository;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.service.ComunicacaoService;
import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;
import br.gov.mme.service.dto.NotificacaoCadastroDTO;
import br.gov.mme.service.mapper.ComunicacaoMapper;
import br.gov.mme.util.EmailUtil;
import br.gov.mme.web.rest.util.PaginationUtil;
import br.gov.mme.web.rest.util.QueryUtil;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class ComunicacaoServiceImpl implements ComunicacaoService {

	

	private final ComunicacaoRepository comunicacaoRepository;

	private final PessoaJuridicaRepository pessoaJuridicaRepository;

	private final NotificacaoPessoaJuridicaRepository notificacaoPessoaJuridicaRepository;

	private final ComunicacaoMapper comunicacaMapper;
	
	EmailUtil email = new EmailUtil();

	public ComunicacaoServiceImpl(ComunicacaoRepository comunicacaoRepository,
			PessoaJuridicaRepository pessoaJuridicaRepository,
			NotificacaoPessoaJuridicaRepository notificacaoPessoaJuridicaRepository,
			ComunicacaoMapper comunicacaMapper) {
		this.comunicacaoRepository = comunicacaoRepository;
		this.pessoaJuridicaRepository = pessoaJuridicaRepository;
		this.notificacaoPessoaJuridicaRepository = notificacaoPessoaJuridicaRepository;
		this.comunicacaMapper = comunicacaMapper;
	}

	@Override
	public NotificacaoCadastroDTO salvarNotificacao(NotificacaoCadastroDTO notificacaoDto) {

		if (notificacaoDto.getId() == null) {
			notificacaoDto.setDataCadastro(LocalDateTime.now());
		}

		Notificacao notificacao = comunicacaMapper.toEntity(notificacaoDto);

//		email.enviar("fabiotogawa@gmail.com", "Email de teste", "Email de teste 123");

		notificacao = comunicacaoRepository.save(notificacao);

		if (notificacao != null) {
			salvaNotificacaoPessoaJuridica(notificacao, notificacaoDto);
		}

		return comunicacaMapper.toDto(notificacao);
	}

	private void salvaNotificacaoPessoaJuridica(Notificacao notificacaoSalva, NotificacaoCadastroDTO notificacaoDto) {
		NotificacaoPessoaJuridica notificacaoPessoaJuridica = new NotificacaoPessoaJuridica();
		notificacaoPessoaJuridica.setNotificacaoPessoaJuridicaId(new NotificacaoPessoaJuridicaId());
		PessoaJuridica pessoaJuridica = new PessoaJuridica();

		if (notificacaoDto.getRepresentantes().size() > 0) {
			for (ComunicacaoRepresentantelistaDTO rep : notificacaoDto.getRepresentantes()) {
				pessoaJuridica = pessoaJuridicaRepository.findOne(rep.getIdPessoaJuridica());
				notificacaoPessoaJuridica.getNotificacaoPessoaJuridicaId().setNotificacao(notificacaoSalva);
				notificacaoPessoaJuridica.getNotificacaoPessoaJuridicaId().setPessoaJuridica(pessoaJuridica);
				notificacaoPessoaJuridicaRepository.save(notificacaoPessoaJuridica);
				notificacaoPessoaJuridicaRepository.flush();
			}
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Page<ComunicacaoRepresentantelistaDTO> listarRepresentantes(String filtro, Pageable pageable) {

		if (StringUtils.isBlank(filtro)) {
			return comunicacaoRepository.listarRepresentantes(PaginationUtil.ignoreCase(pageable));
		} else {
			return comunicacaoRepository.listarRepresentantesComFiltro(QueryUtil.preparaStringLike(filtro),
					PaginationUtil.ignoreCase(pageable));
		}
	}



}
