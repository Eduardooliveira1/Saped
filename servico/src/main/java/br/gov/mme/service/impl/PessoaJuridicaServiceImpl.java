package br.gov.mme.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.domain.Pessoa;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.enumeration.FlStatus;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.repository.PessoaRepository;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.service.mapper.PessoaJuridicaMapper;
import br.gov.mme.service.util.ValidatorUtils;
import br.gov.mme.web.rest.PessoaJuridicaResource;
import br.gov.mme.web.rest.errors.EntityNotFoundException;
import br.gov.mme.web.rest.errors.ErrorKeys;
import br.gov.mme.web.rest.errors.IdAlreadyExistsException;
import br.gov.mme.web.rest.errors.InvalidFieldException;
import br.gov.mme.web.rest.util.PaginationUtil;
import br.gov.mme.web.rest.util.QueryUtil;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    private final PessoaRepository pessoaRepository;

    private final PessoaJuridicaMapper pessoaJuridicaMapper;

    public static final String EMPRESA_JA_CADASTRADA = "Esta empresa já está cadastrada no sistema.";

    public static final String CNPJ_INVALIDO = "CNPJ invalido.";

    public static final String ENTITY_NAME = "pessoa-juridica";
    
    public static final String PESSOA_NAO_CADASTRADA = 
            "Não é possível excluir uma pessoa jurídica inexistente!";

    public PessoaJuridicaServiceImpl(PessoaJuridicaRepository pessoaJuridicaRepository,
            PessoaJuridicaMapper pessoaJuridicaMapper, PessoaRepository pessoaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaJuridicaMapper = pessoaJuridicaMapper;
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PessoaJuridicaListaDTO> listarPessoasJuridicas(String filtro, Pageable pageable) {

        if (StringUtils.isBlank(filtro)) {
            return pessoaJuridicaRepository.listarPessoasJuridicas(PaginationUtil.ignoreCase(pageable));
        } else {
            return pessoaJuridicaRepository.listarPessoasJuridicasComFiltro(QueryUtil.preparaStringLike(filtro),
                    PaginationUtil.ignoreCase(pageable));
        }
    }

    @Override
    public PessoaJuridicaCadastroDTO salvarPessoaJuridica(PessoaJuridicaCadastroDTO pessoaJuridicaDto)
            throws IdAlreadyExistsException, InvalidFieldException {

        PessoaJuridicaCadastroDTO p = pessoaJuridicaRepository.findByCnpj(pessoaJuridicaDto.getCnpj());

        if (Objects.nonNull(p) && !p.getId().equals(pessoaJuridicaDto.getId())) {
            throw new IdAlreadyExistsException(EMPRESA_JA_CADASTRADA, 
                    PessoaJuridicaResource.ENTITY_NAME);
        }

        if (!ValidatorUtils.cnpjValido(pessoaJuridicaDto.getCnpj())) {
            throw new InvalidFieldException(CNPJ_INVALIDO, 
                    PessoaJuridicaResource.ENTITY_NAME, ErrorKeys.CNPJ_INVALID);
        }

        PessoaJuridica pessoaJuridica = pessoaJuridicaMapper.toEntity(pessoaJuridicaDto);

        if (Objects.isNull(pessoaJuridicaDto.getId())) {
            pessoaJuridica.setPessoa(new Pessoa().setStatus(FlStatus.S).setDataCadastro(LocalDateTime.now()));
        } else {
            pessoaJuridica.setPessoa(pessoaRepository.findOne(pessoaJuridicaDto.getId()));
        }

        pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
        return pessoaJuridicaMapper.toDto(pessoaJuridica);
    }

    @Override
    public PessoaJuridicaCadastroDTO obterPordId(Long id) {
        return pessoaJuridicaMapper.toDto(pessoaJuridicaRepository.findOne(id));
    }

    @Override
    public void excluirPessoaJuridica(Long id) throws EntityNotFoundException {
        Pessoa pessoa = pessoaRepository.findOne(id);
        if (pessoa == null) {
            throw new EntityNotFoundException(PESSOA_NAO_CADASTRADA, ENTITY_NAME);
        }
        pessoa.setStatus(FlStatus.N);
        pessoaRepository.save(pessoa);
    }
}
