package br.gov.mme.service;


import br.gov.mme.service.dto.NotificacaoCadastroDTO;
import br.gov.mme.service.impl.ComunicacaoServiceImpl;

/**
 * Service Interface for managing Comunicacao.
 * @see ComunicacaoServiceImpl
 */
public interface ComunicacaoService {

    /**
     * Salva notificacao
     * @param notificacao
     * @return notificacao salva
     */
    NotificacaoCadastroDTO salvarNotificacao(NotificacaoCadastroDTO notificacao);
    



}
