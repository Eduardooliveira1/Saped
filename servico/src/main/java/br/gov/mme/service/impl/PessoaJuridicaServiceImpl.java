package br.gov.mme.service.impl;

import br.gov.mme.domain.Pessoa;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.domain.Representante;
import br.gov.mme.domain.Telefone;
import br.gov.mme.enumeration.FlStatus;
import br.gov.mme.exceptions.CnpjInvalidoException;
import br.gov.mme.exceptions.CreatePJWithExistentIdException;
import br.gov.mme.exceptions.DeleteInexistentPJException;
import br.gov.mme.exceptions.EntityNotExistException;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.repository.PessoaRepository;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaComboDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.service.dto.PessoaRepresentantelistaDTO;
import br.gov.mme.service.mapper.PessoaJuridicaMapper;
import br.gov.mme.service.mapper.PessoaRepresentanteMapper;
import br.gov.mme.service.util.ValidatorUtils;
import br.gov.mme.web.rest.PessoaJuridicaResource;
import br.gov.mme.web.rest.util.PaginationUtil;
import br.gov.mme.web.rest.util.QueryUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    private final PessoaRepository pessoaRepository;

    private final PessoaJuridicaMapper pessoaJuridicaMapper;

    private final PessoaRepresentanteMapper pessoaRepresentanteMapper;

    public static final String ENTITY_NAME = "pessoa-juridica";

    private final Logger log = LoggerFactory.getLogger(PessoaJuridicaServiceImpl.class);


    public PessoaJuridicaServiceImpl(PessoaJuridicaRepository pessoaJuridicaRepository,
                                     PessoaJuridicaMapper pessoaJuridicaMapper, PessoaRepository pessoaRepository,
                                     PessoaRepresentanteMapper pessoaRepresentanteMapper) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.pessoaJuridicaMapper = pessoaJuridicaMapper;
        this.pessoaRepository = pessoaRepository;
        this.pessoaRepresentanteMapper = pessoaRepresentanteMapper;
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
            throws CreatePJWithExistentIdException, CnpjInvalidoException {

        PessoaJuridicaCadastroDTO p = pessoaJuridicaRepository.findByCnpj(pessoaJuridicaDto.getCnpj());

        if (Objects.nonNull(p) && !p.getId().equals(pessoaJuridicaDto.getId())) {
            throw new CreatePJWithExistentIdException();
        }

        if (!ValidatorUtils.cnpjValido(pessoaJuridicaDto.getCnpj())) {
            throw new CnpjInvalidoException();
        }

        PessoaJuridica pessoaJuridica = pessoaJuridicaMapper.toEntity(pessoaJuridicaDto);

        if (pessoaJuridicaDto.getId() == null) {
            pessoaJuridica.setPessoa(new Pessoa().setStatus(FlStatus.S).setDataCadastro(LocalDateTime.now()));
        } else {
            pessoaJuridica.setPessoa(pessoaRepository.findOne(pessoaJuridicaDto.getId()));
        }

        atribuirRepresentantes(pessoaJuridica);

        pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
        return pessoaJuridicaMapper.toDto(pessoaJuridica);
    }

    private void atribuirRepresentantes(PessoaJuridica pessoaJuridica) {
        for (Representante representante : pessoaJuridica.getRepresentantes()) {
            if (representante.getId() == null) {
                representante.getPessoa().setStatus(FlStatus.S);
                representante.getPessoa().setDataCadastro(LocalDateTime.now());
            } else {
                representante.setPessoa(pessoaRepository.findOne(representante.getId()));
            }

            representante.setPessoaJuridica(pessoaJuridica);
            atribuiPessoaaoTelefone(representante);
        }
    }

    private void atribuiPessoaaoTelefone(Representante representante) {
        for (Telefone telefone : representante.getTelefone()) {
            telefone.setPessoaRepresentante(representante);
            telefone.setStatus(FlStatus.S);
        }
    }

    @Override
    public PessoaJuridicaCadastroDTO obterPordId(Long id) {
        return pessoaJuridicaMapper.toDto(pessoaJuridicaRepository.findOne(id));
    }

    @Override
    public void excluirPessoaJuridica(Long id) throws DeleteInexistentPJException {
        Pessoa pessoa = pessoaRepository.findOne(id);
        if (pessoa == null) {
            throw new DeleteInexistentPJException();
        }
        pessoa.setStatus(FlStatus.N);
        pessoaRepository.save(pessoa);
    }

    @Override
    public void verificaExistenciaNovaPJ(Long id) throws CreatePJWithExistentIdException {
        if (id != null) {
            throw new CreatePJWithExistentIdException();
        }
    }

    @Override
    public List<PessoaJuridicaComboDTO> listarTodas() {
        return pessoaJuridicaRepository.listarTodas();
    }

    public List<PessoaRepresentantelistaDTO> obterRepresentantesPorIdPj(Long idPj) throws EntityNotExistException {
        try{
            PessoaJuridica pessoaJuridica = pessoaJuridicaRepository.findOne(idPj);

        List<PessoaRepresentantelistaDTO> lista = new ArrayList<PessoaRepresentantelistaDTO>();

        for (Representante representante : pessoaJuridica.getRepresentantes()) {
            lista.add(pessoaRepresentanteMapper.toDtoLista(representante));
        }
        return lista;

        } catch (IllegalArgumentException e){
            log.error(e.getMessage(), e);
            throw new EntityNotExistException();
        }
    }
}
