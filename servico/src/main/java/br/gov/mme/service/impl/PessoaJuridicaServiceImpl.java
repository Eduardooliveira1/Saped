package br.gov.mme.service.impl;

import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.service.mapper.PessoaJuridicaListaMapper;
import br.gov.mme.web.rest.util.PaginationUtil;
import br.gov.mme.web.rest.util.QueryUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final PessoaJuridicaListaMapper pessoaJuridicaListaMapper;

    public PessoaJuridicaServiceImpl(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaJuridicaListaMapper pessoaJuridicaListaMapper) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaJuridicaListaMapper = pessoaJuridicaListaMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PessoaJuridicaListaDTO> listarPessoasJuridicas(String filtro, Pageable pageable) {
        Page<PessoaJuridica> result = StringUtils.isBlank(filtro)
                ? pessoaJuridicaRepository.listarPessoasJuridicas(PaginationUtil.ignoreCase(pageable))
                : pessoaJuridicaRepository.listarPessoasJuridicasComFiltro(QueryUtil.preparaStringLike(filtro), PaginationUtil.ignoreCase(pageable));

        return result.map(pessoaJuridicaListaMapper::toDto);
    }
}
