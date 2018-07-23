package br.gov.mme.service.impl;

import br.gov.mme.enumeration.PersistentEnum;
import br.gov.mme.enumeration.TpEndereco;
import br.gov.mme.service.EnumerationService;
import br.gov.mme.service.dto.EnumerationDTO;
import br.gov.mme.service.mapper.EnumerationMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnumerationServiceImpl implements EnumerationService {


    private final EnumerationMapper enumerationMapper;

    public EnumerationServiceImpl(EnumerationMapper enumerationMapper) {
        this.enumerationMapper = enumerationMapper;
    }

    @Override
    public List<EnumerationDTO> getAllTipoEndereco() {
        List<EnumerationDTO> enumerationDTOList = new ArrayList<>();
        for (PersistentEnum<?> enumConstant : TpEndereco.class.getEnumConstants()) {
            enumerationDTOList.add(enumerationMapper.toDto(enumConstant));
        }
        return enumerationDTOList;
    }

}
