package br.gov.mme.util;

import java.lang.reflect.Type;

import com.google.common.reflect.TypeToken;

public final class GenericTypeUtil<T> {

    private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
        private static final long serialVersionUID = 1L;
    };

    private final Type type = typeToken.getType();

    public Type getType() {
        return this.type;
    }

    public String getName() {
        return type.getClass().getSimpleName();
    }

}
