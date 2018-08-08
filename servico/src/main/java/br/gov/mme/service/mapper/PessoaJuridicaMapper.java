package br.gov.mme.service.mapper;

import org.mapstruct.Mapper;

import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;

@Mapper(componentModel = "spring", uses = {PessoaRepresentanteMapper.class})
public interface PessoaJuridicaMapper  {

    PessoaJuridica toEntity(PessoaJuridicaCadastroDTO dto);
    PessoaJuridicaCadastroDTO toDto(PessoaJuridica entity);

}
