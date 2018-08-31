package br.gov.mme.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.exceptions.CNPJInvalidoException;
import br.gov.mme.exceptions.CreateEntityWithExistentIdException;
import br.gov.mme.exceptions.DeleteInexistentEntityException;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaComboDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.service.dto.PessoaJuridicaNomeDTO;
import br.gov.mme.service.impl.PessoaJuridicaServiceImpl;

/**
 * Service Interface for managing PessoaJuridica.
 * 
 * @see PessoaJuridicaServiceImpl
 */
public interface PessoaJuridicaService {

    /**
     * Lita pessoas jurídicas
     * 
     * @param pageable
     * @return a lista de pessoa juridica
     */
    Page<PessoaJuridicaListaDTO> listarPessoasJuridicas(String filtro, Pageable pageable);

    /**
     * Salva pessoa juridica
     * 
     * @param pessoaJuridica
     * @return pessoa juridica salva
     * @throws IdAlreadyExistsException
     * @throws CNPJInvalidoException
     * @throws CreateEntityWithExistentIdException
     */
    PessoaJuridicaCadastroDTO salvarPessoaJuridica(PessoaJuridicaCadastroDTO pessoaJuridica)
        throws CreateEntityWithExistentIdException,
        CNPJInvalidoException;

    /**
     * Obtem pessoa pessoa jurídica por id
     * 
     * @param id
     * @return pessoa juridica
     */

    PessoaJuridicaCadastroDTO obterPordId(Long id);

    /**
     * remover pessoa juridica
     * 
     * @param id
     * @throws DeleteInexistentPJException
     */
    void excluirPessoaJuridica(Long id) throws DeleteInexistentEntityException;

    /**
     * Verifica se a pessoa juridica existe
     * 
     * @param id
     * @throws CreatePJWithExistentIdException
     */
    void verificaExistenciaNovaPJ(Long id) throws CreateEntityWithExistentIdException;

    List<PessoaJuridicaComboDTO> listarTodas();

    List<PessoaJuridicaNomeDTO> getNomesByPJ();

    PessoaJuridica findOne(Long idPessoaJuridica);
}
