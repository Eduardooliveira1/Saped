package br.gov.mme.service;


import java.io.File;
import java.io.FileOutputStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.mme.exceptions.CnpjInvalidoException;
import br.gov.mme.exceptions.CreatePJWithExistentIdException;
import br.gov.mme.exceptions.DeleteInexistentPJException;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;

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
     * @throws CnpjInvalidoException
     * @throws CreatePJWithExistentIdException
     */
    PessoaJuridicaCadastroDTO salvarPessoaJuridica(PessoaJuridicaCadastroDTO pessoaJuridica)
            throws CnpjInvalidoException, CreatePJWithExistentIdException;

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
     * @throws DeleteInexistentPJException
     */
    void excluirPessoaJuridica(Long id) throws DeleteInexistentPJException;

    /**
     * Verifica se a pessoa juridica existe
     * 
     * @param id
     * @throws CreatePJWithExistentIdException
     */
    void verificaExistenciaNovaPJ(Long id) throws CreatePJWithExistentIdException;

    /**
     * 
     * @param file
     * @return Arquivo exportado do relatório
     */
    FileOutputStream getExportFile(File file);

}
