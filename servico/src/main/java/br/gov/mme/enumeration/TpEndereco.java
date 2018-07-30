package br.gov.mme.enumeration;


import br.gov.mme.service.util.EnumSerializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.stream.Stream;

@JsonSerialize(using = EnumSerializer.class)
public enum TpEndereco  implements ConverterEnum<TpEndereco> {
    CB("CB","Cobrança"),CM("CM","Comercial");

    private String id;

    private String descricao;

    TpEndereco(String id, String descricao) {
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

    public static TpEndereco fromId(String id) {
        final String t = id.trim().toUpperCase();
        return Stream.of(TpEndereco.values())
                .filter(e -> e.getId().equals(t))
                .findFirst()
                .orElseGet(null);
    }

    @JsonCreator()
    public static TpEndereco simNao(@JsonProperty("id") String id) {
        return id == null ? null : TpEndereco.valueOf(id);
    }

}
