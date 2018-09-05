package br.gov.mme.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.domain.Pessoa;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.domain.Representante;
import br.gov.mme.domain.Telefone;
import br.gov.mme.enumeration.FlStatus;
import br.gov.mme.exceptions.CNPJInvalidoException;
import br.gov.mme.exceptions.CreateEntityWithExistentIdException;
import br.gov.mme.exceptions.DeleteInexistentEntityException;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.repository.PessoaRepository;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaComboDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.service.dto.PessoaJuridicaNomeDTO;
import br.gov.mme.service.mapper.PessoaJuridicaMapper;
import br.gov.mme.service.util.ValidatorUtils;
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

    public static final String ENTITY_NAME = "pessoa-juridica";

    public PessoaJuridicaServiceImpl(PessoaJuridicaRepository pessoaJuridicaRepository,
                                     PessoaJuridicaMapper pessoaJuridicaMapper,
                                     PessoaRepository pessoaRepository) {
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
            return pessoaJuridicaRepository.listarPessoasJuridicasComFiltro(QueryUtil
                .preparaStringLike(filtro), PaginationUtil.ignoreCase(pageable));
        }
    }

    @Override
    public PessoaJuridicaCadastroDTO salvarPessoaJuridica(PessoaJuridicaCadastroDTO pessoaJuridicaDto)
            throws CreateEntityWithExistentIdException, CNPJInvalidoException
    {

        PessoaJuridicaCadastroDTO p = pessoaJuridicaRepository.findByCnpj(pessoaJuridicaDto.getCnpj());
        
        if (Objects.nonNull(p) && !p.getId().equals(pessoaJuridicaDto.getId()) && p.getStatus().equals(FlStatus.S)) {
            throw new CreateEntityWithExistentIdException(ENTITY_NAME);
        }else if(Objects.nonNull(p) && !p.getId().equals(pessoaJuridicaDto.getId()) && p.getStatus().equals(FlStatus.N)){
        	pessoaJuridicaDto.setId(p.getId());
        	pessoaJuridicaDto.setStatus(p.getStatus());
        }

        if (!ValidatorUtils.cnpjValido(pessoaJuridicaDto.getCnpj())) {
            throw new CNPJInvalidoException();
        }

        PessoaJuridica pessoaJuridica = pessoaJuridicaMapper.toEntity(pessoaJuridicaDto);

        if (pessoaJuridicaDto.getId() == null) {
            pessoaJuridica.setPessoa(new Pessoa().setStatus(FlStatus.S)
                .setDataCadastro(LocalDateTime.now()));
        } else {
        	pessoaJuridica.setPessoa(pessoaRepository.findOne(pessoaJuridicaDto.getId()));
        	pessoaJuridica.setRepresentantes(recuperaEAdicionaRepresentantesSalvos(pessoaJuridicaDto.getId(),pessoaJuridica));
        	if(pessoaJuridicaDto.getStatus() != null && pessoaJuridicaDto.getStatus().equals(FlStatus.N)) {
        		pessoaJuridica.getPessoa().setStatus(FlStatus.S);
        		pessoaJuridica.getPessoa().setDataCadastro(LocalDateTime.now());
        	}
        }

        atribuirRepresentantes(pessoaJuridica);
        
        pessoaJuridicaRepository.flush();
        pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
        return pessoaJuridicaMapper.toDto(pessoaJuridica);
    }

    private List<Representante> recuperaEAdicionaRepresentantesSalvos(Long idPessoaJuridica, PessoaJuridica pessoaJuridica) {
    		PessoaJuridica recuperarPessoaJuridicaSalva =  pessoaJuridicaRepository.findOne(idPessoaJuridica);
    		
    		for(Representante rep : pessoaJuridica.getRepresentantes() ) {
    			if(rep.getId() == null) {
    				recuperarPessoaJuridicaSalva.getRepresentantes().add(rep);	
    			}
           		
           }
          
           return recuperarPessoaJuridicaSalva.getRepresentantes();
           
	}

	private void atribuirRepresentantes(PessoaJuridica pessoaJuridica) {
        for (Representante representante : pessoaJuridica.getRepresentantes()) {
            if (representante.getId() == null) {
                representante.getPessoa()
                    .setStatus(FlStatus.S);
                representante.getPessoa()
                    .setDataCadastro(LocalDateTime.now());
            } else {
                representante.setPessoa(pessoaRepository.findOne(representante.getId()));
            }

            representante.setPessoaJuridica(pessoaJuridica);
            atribuiPessoaAoTelefone(representante);
        }
    }

    private void atribuiPessoaAoTelefone(Representante representante) {
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
    public void excluirPessoaJuridica(Long id) throws DeleteInexistentEntityException {
        Pessoa pessoa = pessoaRepository.findOne(id);
        if (pessoa == null) {
            throw new DeleteInexistentEntityException(ENTITY_NAME);
        }
        pessoa.setStatus(FlStatus.N);
        pessoaRepository.save(pessoa);
    }

    @Override
    public void verificaExistenciaNovaPJ(Long id) throws CreateEntityWithExistentIdException {
        if (id != null) {
            throw new CreateEntityWithExistentIdException(ENTITY_NAME);
        }
    }

    @Override
    public List<PessoaJuridicaComboDTO> listarTodas() {
        return pessoaJuridicaRepository.listarTodas();
    }

    @Override
    public List<PessoaJuridicaNomeDTO> getNomesByPJ() {
        return pessoaJuridicaRepository.getNomesByPJ();
    }

    @Override
    public PessoaJuridica findOne(Long idPessoaJuridica) {
        return pessoaJuridicaRepository.findOne(idPessoaJuridica);
    }

}
