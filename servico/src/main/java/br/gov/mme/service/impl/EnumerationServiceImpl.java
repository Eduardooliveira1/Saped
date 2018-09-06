package br.gov.mme.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.mme.enumeration.ConverterEnum;
import br.gov.mme.enumeration.TpEndereco;
import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.service.EnumerationService;
import br.gov.mme.service.dto.EnumerationDTO;
import br.gov.mme.service.dto.PessoaJuridicaNomeDTO;
import br.gov.mme.service.mapper.EnumerationMapper;

@Service
public class EnumerationServiceImpl implements EnumerationService {


    private final EnumerationMapper enumerationMapper;


    public EnumerationServiceImpl(EnumerationMapper enumerationMapper) {
        this.enumerationMapper = enumerationMapper;
    }

    @Override
    public List<EnumerationDTO> getAllTipoEndereco() {
        return this.getAllBase(TpEndereco.class);
    }

    @Override
    public List<EnumerationDTO> getAllStatusBoleto() {
        return this.getAllBase(TpStatusBoleto.class);
    }

    private List<EnumerationDTO> getAllBase(Class<?> enun) {
        List<EnumerationDTO> enumerationDTOList = new ArrayList<>();
        for (ConverterEnum<?> enumConstant : (ConverterEnum<?>[]) enun.getEnumConstants()) {
            enumerationDTOList.add(enumerationMapper.toDto(enumConstant));
        }
        return enumerationDTOList;
    }

    @Override
    public List<EnumerationDTO> getAllNomesPJ(List<PessoaJuridicaNomeDTO> nomesPessoasJuridicas) {
        List<EnumerationDTO> enumerationDTOList = new ArrayList<>();
        nomesPessoasJuridicas.forEach(nome -> {
            enumerationDTOList.add(enumerationMapper.toDto(nome));
        });
        return enumerationDTOList;
    }

}
