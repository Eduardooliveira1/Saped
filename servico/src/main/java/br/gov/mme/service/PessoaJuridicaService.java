package br.gov.mme.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.service.impl.PessoaJuridicaServiceImpl;

/**
 * Service Interface for managing PessoaJuridica.
 * @see PessoaJuridicaServiceImpl
 */
public interface PessoaJuridicaService {

    /**
     * Lita pessoas jurídicas
     * @param pageable
     * @return a lista de pessoa juridica
     */
    Page<PessoaJuridicaListaDTO> listarPessoasJuridicas(String filtro,Pageable pageable);

}
