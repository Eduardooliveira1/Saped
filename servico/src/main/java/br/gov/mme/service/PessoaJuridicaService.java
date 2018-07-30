package br.gov.mme.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.service.impl.PessoaJuridicaServiceImpl;
import br.gov.mme.web.rest.errors.BadRequestAlertException;

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

    /**
     * Salva pessoa juridica
     * @param pessoaJuridica
     * @return pessoa juridica salva
     */
    PessoaJuridicaCadastroDTO salvarPessoaJuridica(PessoaJuridicaCadastroDTO pessoaJuridica);

    /**
     * Obtem pessoa pessoa jurídica por id
     * @param id
     * @return pessoa juridica
     */

    PessoaJuridicaCadastroDTO obterPordId(Long id);


    /**
     * remover pessoa juridica
     * @param id
     */
    void excluirPessoaJuridica(Long id) throws BadRequestAlertException;

}
