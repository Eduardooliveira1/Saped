package br.gov.mme.service.impl;

import java.time.LocalDateTime;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.domain.Notificacao;
import br.gov.mme.repository.ComunicacaoRepository;
import br.gov.mme.service.ComunicacaoService;
import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;
import br.gov.mme.service.dto.NotificacaoCadastroDTO;
import br.gov.mme.service.mapper.ComunicacaoMapper;
import br.gov.mme.web.rest.util.PaginationUtil;
import br.gov.mme.web.rest.util.QueryUtil;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class ComunicacaoServiceImpl implements ComunicacaoService {


    private final ComunicacaoRepository comunicacaoRepository;

    private final ComunicacaoMapper comunicacaMapper;

    public ComunicacaoServiceImpl(ComunicacaoRepository comunicacaoRepository,
    		ComunicacaoMapper comunicacaMapper) {
        this.comunicacaoRepository = comunicacaoRepository;
        this.comunicacaMapper = comunicacaMapper;
    }


    @Override
    public NotificacaoCadastroDTO salvarNotificacao(NotificacaoCadastroDTO notificacaoDto) {
    	if (notificacaoDto.getId() == null) {
    		notificacaoDto.setDataCadastro(LocalDateTime.now());
         }
    	
    	Notificacao notificacao = comunicacaMapper.toEntity(notificacaoDto);
    	notificacao = comunicacaoRepository.save(notificacao);
    	return comunicacaMapper.toDto(notificacao);
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
