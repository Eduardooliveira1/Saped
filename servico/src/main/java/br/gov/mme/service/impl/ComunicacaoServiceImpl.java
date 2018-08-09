package br.gov.mme.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.repository.PessoaRepository;
import br.gov.mme.service.ComunicacaoService;
import br.gov.mme.service.dto.NotificacaoCadastroDTO;
import br.gov.mme.service.mapper.PessoaJuridicaMapper;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class ComunicacaoServiceImpl implements ComunicacaoService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    private final PessoaRepository pessoaRepository;

    private final PessoaJuridicaMapper pessoaJuridicaMapper;

    private static final String EMPRESA_JA_CADASTRADA = "Esta empresa já está cadastrada no sistema.";

    private static final String CNPJ_INVALIDO = "CNPJ inválido.";

    public ComunicacaoServiceImpl(PessoaJuridicaRepository pessoaJuridicaRepository,
            PessoaJuridicaMapper pessoaJuridicaMapper, PessoaRepository pessoaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaJuridicaMapper = pessoaJuridicaMapper;
        this.pessoaRepository = pessoaRepository;
    }


    @Override
    public NotificacaoCadastroDTO salvarNotificacao(NotificacaoCadastroDTO notificacaoDto) {
    	return null;
    }



}
