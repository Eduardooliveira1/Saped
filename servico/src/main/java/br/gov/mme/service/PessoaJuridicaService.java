package br.gov.mme.service;

import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

}
