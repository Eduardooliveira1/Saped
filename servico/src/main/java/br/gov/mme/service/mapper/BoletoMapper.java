package br.gov.mme.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.gov.mee.vo.BoletoRelatorioPagamentoVO;
import br.gov.mme.domain.Boleto;

@Mapper(componentModel = "spring", uses = {})
public interface BoletoMapper {

    @Mappings({ @Mapping(target = "statusBoleto", source = "tpBoleto"),
            @Mapping(target = "cnpj", source = "boleto.pessoaJuridica.cnpj"),
            @Mapping(target = "nomeFantasia", source = "boleto.pessoaJuridica.nomeFantasia"),
            @Mapping(target = "dataSegundaVia", source = "boleto.dataVencimento") })
    BoletoRelatorioPagamentoVO toVO(Boleto boleto);

}
