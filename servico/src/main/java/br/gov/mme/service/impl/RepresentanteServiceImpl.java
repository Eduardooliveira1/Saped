package br.gov.mme.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.repository.RepresentanteRepository;
import br.gov.mme.service.RepresentanteService;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;
import br.gov.mme.service.dto.RepresentanteEMailECNPJDTO;
import br.gov.mme.service.dto.TelefoneListaRepresentanteDTO;

/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class RepresentanteServiceImpl implements RepresentanteService {

    private final RepresentanteRepository representanteRepository;

    public RepresentanteServiceImpl(RepresentanteRepository representanteRepository) {
        this.representanteRepository = representanteRepository;
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
    public Boolean verificaCNPJEEmailValidos(RepresentanteEMailECNPJDTO representanteEMailECNPJDTO) {
        RepresentanteEMailECNPJDTO dadosRetornados = representanteRepository.findEmailECNPJ(
                representanteEMailECNPJDTO.getEmail(),
                representanteEMailECNPJDTO.getCnpj());
        return (dadosRetornados != null && dadosRetornados.getEmail() != null && dadosRetornados.getCnpj() != null);
    }
}
