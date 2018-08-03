package br.gov.mme.service.mapper;

import org.mapstruct.Mapper;

import br.gov.mee.vo.BoletoRelatorioPagamentoVO;
import br.gov.mme.domain.Boleto;

@Mapper(componentModel = "spring", uses = {})
public interface BoletoMapper {

    BoletoRelatorioPagamentoVO toVO(Boleto boleto);

    Boleto toEntity(BoletoRelatorioPagamentoVO filtro);

}
