package br.gov.mme.service.mapper;

import br.gov.mme.domain.Representante;
import br.gov.mme.service.dto.PessoaRepresentanteCadastroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PessoaJuridicaMapper.class})
public interface PessoaRepresentanteMapper {

    @Mapping(source = "email",target="pessoa.email")
    Representante toEntity(PessoaRepresentanteCadastroDTO dto);

    @Mapping(source = "pessoa.email",target="email")
    PessoaRepresentanteCadastroDTO toDto(Representante entity);

}
