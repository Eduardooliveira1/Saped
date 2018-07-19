package br.gov.mme.service.mapper;

import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface PessoaJuridicaListaMapper extends EntityMapper<PessoaJuridicaListaDTO, PessoaJuridica> {


    default PessoaJuridica fromId(Long id) {
        if (id == null) {
            return null;
        }
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setId(id);
        return pessoaJuridica;
    }
}
