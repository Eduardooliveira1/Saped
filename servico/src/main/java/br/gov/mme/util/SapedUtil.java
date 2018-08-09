package br.gov.mme.util;

import java.util.ArrayList;
import java.util.List;

public final class SapedUtil {

    private SapedUtil() { }

    public static <T> List<T> instanciarLista(List<T> lista) {
        if (lista == null) {
            lista = new ArrayList<T>();
        }
        return lista;
    }
}
