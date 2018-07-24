package br.gov.mme.enumeration;


import br.gov.mme.service.util.EnumSerializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.stream.Stream;

@JsonSerialize(using = EnumSerializer.class)
public enum TpEndereco  implements PersistentEnum<TpEndereco> {
    CB("CB","Cobrança"),CM("CM","Comercial");

    private String codigo;

    private String descricao;

    TpEndereco(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    public static TpEndereco fromCodigo(String codigo) {
        final String t = codigo.trim().toUpperCase();
        return Stream.of(TpEndereco.values())
                .filter(e -> e.getCodigo().equals(t))
                .findFirst()
                .orElseGet(null);
    }

    @JsonCreator()
    public static TpEndereco simNao(@JsonProperty("codigo") String codigo) {
        return codigo == null ? null : TpEndereco.valueOf(codigo);
    }

}
