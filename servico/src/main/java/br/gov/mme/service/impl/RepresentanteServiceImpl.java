package br.gov.mme.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.repository.RepresentanteRepository;
import br.gov.mme.service.MailSenderService;
import br.gov.mme.service.RepresentanteService;
import br.gov.mme.service.UrlService;
import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;
import br.gov.mme.service.dto.RepresentanteEMailECNPJDTO;
import br.gov.mme.service.dto.TelefoneListaRepresentanteDTO;
import br.gov.mme.service.util.EMailUtils;
import br.gov.mme.web.rest.util.PaginationUtil;
import br.gov.mme.web.rest.util.QueryUtil;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class RepresentanteServiceImpl implements RepresentanteService {

    private final RepresentanteRepository representanteRepository;

    private final MailSenderService mailSenderService;

    private final UrlService urlService;

    public RepresentanteServiceImpl(RepresentanteRepository representanteRepository,
            MailSenderService mailSenderService, UrlService urlService) {
        this.representanteRepository = representanteRepository;
        this.mailSenderService = mailSenderService;
        this.urlService = urlService;
    }

    @Override
    public List<PessoaRepresentanteListaDTO> findRepresentantesByPessoaJuridica(Long idPj) {
        List<PessoaRepresentanteListaDTO> result = representanteRepository.findRepresentantesByPessoaJuridica(idPj);
        List<TelefoneListaRepresentanteDTO> telefones = representanteRepository.findTelefonesByPessoaJuridica(idPj);
        for (PessoaRepresentanteListaDTO representante: result) {
            representante.setTelefone(telefones.stream().filter(telefone -> telefone.getIdRepresentante().equals(representante.getId())).collect(Collectors.toList()));
        }

        return result;
    }

    @Override
    public Boolean enviaEmailEverificaValidade(RepresentanteEMailECNPJDTO representanteEMailECNPJDTO) {
        RepresentanteEMailECNPJDTO dadosRetornados = representanteRepository.findEmailECNPJ(
                representanteEMailECNPJDTO.getEmail(),
                representanteEMailECNPJDTO.getCnpj());
        if (dadosRetornados != null && dadosRetornados.getEmail() != null && dadosRetornados.getCnpj() != null) {
            this.mailSenderService.enviar(dadosRetornados.getEmail(), EMailUtils.REDEFINIR_LOGIN_EMAIL_HEADER,
                    EMailUtils.getRedefinirSenhaEMailMessage(dadosRetornados.getCnpj(), urlService.getFrontUrl()));
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ComunicacaoRepresentantelistaDTO> listarRepresentantes(String filtro, Pageable pageable) {

        if (StringUtils.isBlank(filtro)) {
            return representanteRepository.listarRepresentantes(PaginationUtil.ignoreCase(pageable));
        } else {
            return representanteRepository.listarRepresentantesComFiltro(QueryUtil
                .preparaStringLike(filtro), PaginationUtil.ignoreCase(pageable));
        }
    }
}
