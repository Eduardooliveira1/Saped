package br.gov.mme.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.domain.Notificacao;
import br.gov.mme.domain.NotificacaoPessoaJuridica;
import br.gov.mme.domain.NotificacaoPessoaJuridicaId;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.repository.ComunicacaoRepository;
import br.gov.mme.service.ComunicacaoService;
import br.gov.mme.service.MailSenderService;
import br.gov.mme.service.NotificacaoPessoaJuridicaService;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;
import br.gov.mme.service.dto.NotificacaoCadastroDTO;
import br.gov.mme.service.mapper.ComunicacaoMapper;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class ComunicacaoServiceImpl implements ComunicacaoService {

    private final ComunicacaoRepository comunicacaoRepository;

    private final PessoaJuridicaService pessoaJuridicaService;

    private final NotificacaoPessoaJuridicaService notificacaoPessoaJuridicaService;

    private final ComunicacaoMapper comunicacaMapper;

    private final MailSenderService mailSenderService;

    public ComunicacaoServiceImpl(ComunicacaoRepository comunicacaoRepository,
                                  PessoaJuridicaService pessoaJuridicaService,
                                  NotificacaoPessoaJuridicaService notificacaoPessoaJuridicaService,
                                  ComunicacaoMapper comunicacaMapper,
                                  MailSenderService mailSenderService) {
        this.comunicacaoRepository = comunicacaoRepository;
        this.pessoaJuridicaService = pessoaJuridicaService;
        this.notificacaoPessoaJuridicaService = notificacaoPessoaJuridicaService;
        this.comunicacaMapper = comunicacaMapper;
        this.mailSenderService = mailSenderService;
    }

    @Override
    public NotificacaoCadastroDTO salvarNotificacao(NotificacaoCadastroDTO notificacaoDto) {

        if (notificacaoDto.getId() == null) {
            notificacaoDto.setDataCadastro(LocalDateTime.now());
        }

        Notificacao notificacao = comunicacaMapper.toEntity(notificacaoDto);

        notificacao = comunicacaoRepository.save(notificacao);
        salvaNotificacaoPessoaJuridica(notificacao, notificacaoDto);

        return comunicacaMapper.toDto(notificacao);
    }

    private void salvaNotificacaoPessoaJuridica(Notificacao notificacaoSalva, NotificacaoCadastroDTO notificacaoDto) {
        NotificacaoPessoaJuridica notificacaoPessoaJuridica = new NotificacaoPessoaJuridica();
        notificacaoPessoaJuridica.setNotificacaoPessoaJuridicaId(new NotificacaoPessoaJuridicaId());
        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        if (notificacaoDto.getRepresentantes()
            .size() > 0) {
            for (ComunicacaoRepresentantelistaDTO rep : notificacaoDto.getRepresentantes()) {
                pessoaJuridica = pessoaJuridicaService.findOne(rep.getIdPessoaJuridica());
                notificacaoPessoaJuridica.getNotificacaoPessoaJuridicaId()
                    .setNotificacao(notificacaoSalva);
                notificacaoPessoaJuridica.getNotificacaoPessoaJuridicaId()
                    .setPessoaJuridica(pessoaJuridica);
                notificacaoPessoaJuridicaService.salvarNotificacaoPessoaJuridica(notificacaoPessoaJuridica);

                mailSenderService.enviar(rep.getEmail(), notificacaoDto.getAssunto(), notificacaoDto.getConteudo());
            }
        }

    }

}
