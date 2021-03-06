package br.gov.mme.service.mapper;

import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PessoaRepresentanteMapper.class})
public interface PessoaJuridicaMapper  {

    PessoaJuridica toEntity(PessoaJuridicaCadastroDTO dto);
    PessoaJuridicaCadastroDTO toDto(PessoaJuridica entity);

}
