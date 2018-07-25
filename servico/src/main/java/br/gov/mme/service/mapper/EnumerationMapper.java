package br.gov.mme.service.mapper;

import br.gov.mme.enumeration.ConverterEnum;
import br.gov.mme.service.dto.EnumerationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface EnumerationMapper {

    @Mapping(expression = "java(converterEnum.name())", target = "id")
    EnumerationDTO toDto(ConverterEnum converterEnum);

}
