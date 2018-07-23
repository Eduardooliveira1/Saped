package br.gov.mme.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.web.rest.util.PaginationUtil;
import br.gov.mme.web.rest.util.QueryUtil;

/**
 * 
 * Service Implementation for managing PessoaJuridica.
 *
 */
@Service
@Transactional
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public PessoaJuridicaServiceImpl(PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PessoaJuridicaListaDTO> listarPessoasJuridicas(String filtro, Pageable pageable) {

        if( StringUtils.isBlank(filtro)){
            return  pessoaJuridicaRepository.listarPessoasJuridicas(PaginationUtil.ignoreCase(pageable));
        }else{
           return pessoaJuridicaRepository.listarPessoasJuridicasComFiltro(QueryUtil.preparaStringLike(filtro), PaginationUtil.ignoreCase(pageable));
        }
    }
}
