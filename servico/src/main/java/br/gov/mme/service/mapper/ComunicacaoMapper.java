package br.gov.mme.service.mapper;

import org.mapstruct.Mapper;

import br.gov.mme.domain.Notificacao;
import br.gov.mme.service.dto.NotificacaoCadastroDTO;

@Mapper(componentModel = "spring")
public interface ComunicacaoMapper  {

    Notificacao toEntity(NotificacaoCadastroDTO dto);
    NotificacaoCadastroDTO toDto(Notificacao entity);

}
