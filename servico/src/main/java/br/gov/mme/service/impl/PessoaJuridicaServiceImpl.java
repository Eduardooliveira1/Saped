package br.gov.mme.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.domain.Pessoa;
import br.gov.mme.enumerator.FlStatus;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.repository.PessoaRepository;
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

	private final PessoaRepository pessoaRepository;

	public PessoaJuridicaServiceImpl(PessoaJuridicaRepository pessoaJuridicaRepository,
			PessoaRepository pessoaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
		this.pessoaRepository = pessoaRepository;
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

	@Override
	@Transactional
	public void excluirPessoaJuridica(Long id) {
		Pessoa pessoa = pessoaRepository.getPessoa(id);
		pessoa.setStatus(FlStatus.N);
		pessoaRepository.saveAndFlush(pessoa);
	}
}
