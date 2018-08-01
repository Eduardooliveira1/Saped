package br.gov.mme.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.web.rest.errors.EntityNotFoundException;
import br.gov.mme.web.rest.errors.IdAlreadyExistsException;
import br.gov.mme.web.rest.errors.InvalidFieldException;

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
     * 
     * @param pessoaJuridica
     * @return pessoa juridica salva
     * @throws IdAlreadyExistsException
     * @throws InvalidFieldException
     */
    PessoaJuridicaCadastroDTO salvarPessoaJuridica(PessoaJuridicaCadastroDTO pessoaJuridica)
            throws IdAlreadyExistsException, InvalidFieldException;

    /**
     * Obtem pessoa pessoa jurídica por id
     * @param id
     * @return pessoa juridica
     */

    PessoaJuridicaCadastroDTO obterPordId(Long id);


    /**
     * remover pessoa juridica
     * 
     * @param id
     * @throws EntityNotFoundException
     */
    void excluirPessoaJuridica(Long id) throws EntityNotFoundException;

    /**
     * Verifica se a pessoa juridica existe
     * 
     * @param id
     * @throws InvalidFieldException
     */
    void verificaExistenciaNovaPJ(Long id) throws InvalidFieldException;

}
