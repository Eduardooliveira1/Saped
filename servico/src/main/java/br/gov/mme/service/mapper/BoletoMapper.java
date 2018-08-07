package br.gov.mme.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import br.gov.mme.domain.Boleto;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;

@Mapper(componentModel = "spring", uses = {})
public interface BoletoMapper {

    @Mappings({ @Mapping(target = "statusBoleto", source = "tpBoleto"),
            @Mapping(target = "cnpj", source = "boleto.pessoaJuridica.cnpj"),
            @Mapping(target = "nomeFantasia", source = "boleto.pessoaJuridica.nomeFantasia"),
            @Mapping(target = "dataSegundaVia", source = "boleto.dataVencimento") })
    BoletoRelatorioPagamentoDTO toVO(Boleto boleto);

}
