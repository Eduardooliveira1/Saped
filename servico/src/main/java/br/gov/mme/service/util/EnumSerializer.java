package br.gov.mme.service.util;

import br.gov.mme.enumeration.ConverterEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EnumSerializer<T> extends JsonSerializer<ConverterEnum<T>> {

    @Override
    public void serialize(ConverterEnum<T> value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("codigo");
        jsonGenerator.writeString(value.name());
        jsonGenerator.writeFieldName("descricao");
        jsonGenerator.writeString(value.getDescricao());
        jsonGenerator.writeEndObject();
    }
}