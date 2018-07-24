package br.gov.mme.service.mapper;

import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PessoaJuridicaMapper  {

    PessoaJuridica toEntity(PessoaJuridicaCadastroDTO dto);
    PessoaJuridicaCadastroDTO toDto(PessoaJuridica entity);

}
