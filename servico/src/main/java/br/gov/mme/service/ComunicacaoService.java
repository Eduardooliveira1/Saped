package br.gov.mme.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;
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
    
    /**
     * Lita representantes
     * @param pageable
     * @return a lista de representantes
     */
    Page<ComunicacaoRepresentantelistaDTO> listarRepresentantes(String filtro,Pageable pageable);


}
