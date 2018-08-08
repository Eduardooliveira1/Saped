package br.gov.mme.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.gov.mme.enumeration.ConverterEnum;
import br.gov.mme.enumeration.TpEndereco;
import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.service.EnumerationService;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.EnumerationDTO;
import br.gov.mme.service.dto.ListaNomePessoaJuridicaDTO;
import br.gov.mme.service.mapper.EnumerationMapper;

@Service
public class EnumerationServiceImpl implements EnumerationService {


    private final EnumerationMapper enumerationMapper;

    private final PessoaJuridicaService pessoaJuridicaService;

    public EnumerationServiceImpl(EnumerationMapper enumerationMapper, 
            PessoaJuridicaService pessoaJuridicaService) {
        this.enumerationMapper = enumerationMapper;
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @Override
    public List<EnumerationDTO> getAllTipoEndereco() {
        return this.getAllBase(TpEndereco.class);
    }

    @Override
    public List<EnumerationDTO> getAllNomesPjs() {
        Set<ListaNomePessoaJuridicaDTO> listaNomesDTO = pessoaJuridicaService.getNomesPessoasJuridicas();
        List<EnumerationDTO> enumDTOList = new ArrayList<>();
        listaNomesDTO.forEach(nome -> {
            enumDTOList.add(enumerationMapper.toDto(nome));
        });
        return enumDTOList;
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

}
