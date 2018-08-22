package br.gov.mme.enumeration;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.gov.mme.service.util.EnumSerializer;

@JsonSerialize(using = EnumSerializer.class)
public enum TpStatusBoleto implements ConverterEnum<TpStatusBoleto>, Serializable {
    AV("AV", "A Vencer"), AD("AD", "Adiantado"), EM("EM", "Emitido"),
    PG("PG", "Pago"), VE("VE", "Vencido");

    private String id;

    private String descricao;

    TpStatusBoleto(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @JsonCreator()
    public static TpStatusBoleto simNao(@JsonProperty("id") String id) {
        return id == null ? null : TpStatusBoleto.valueOf(id);
    }

}