package br.gov.mme.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.gov.mme.enumeration.ConverterEnum;
import br.gov.mme.service.dto.EnumerationDTO;
import br.gov.mme.service.dto.PessoaJuridicaNomeDTO;

@Mapper(componentModel = "spring", uses = {})
public interface EnumerationMapper {

    @Mapping(expression = "java(converterEnum.name())", target = "id")
    EnumerationDTO toDto(ConverterEnum<?> converterEnum);

    @Mapping(expression = "java(br.gov.mme.service.util.FormatterUtils.converterNomeEmpresaToDropdown"
            + "(listaNomesDTO.getNomeFantasia(), listaNomesDTO.getCnpj()))", target = "descricao")
    EnumerationDTO toDto(PessoaJuridicaNomeDTO listaNomesDTO);

}
