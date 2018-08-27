package br.gov.mme.enumeration.converter;

import java.util.EnumSet;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.gov.mme.enumeration.FlSituacaoBancaria;

@Converter
public class FlSituacaoBancariaConverter implements AttributeConverter<FlSituacaoBancaria, String> {

    @Override
    public String convertToDatabaseColumn(FlSituacaoBancaria situacao) {
        return situacao.numeroOp();
    }

    @Override
    public FlSituacaoBancaria convertToEntityAttribute(String situacaoDB) {
        EnumSet<FlSituacaoBancaria> listSituacoes = EnumSet.allOf(FlSituacaoBancaria.class);
        for (FlSituacaoBancaria situacao : listSituacoes) {
            if (situacao.numeroOp().equals(situacaoDB)) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Valor inválido!");
    }
}
