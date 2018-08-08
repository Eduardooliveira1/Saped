package br.gov.mme.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SapedUtil {

    private SapedUtil() { }

    public static <T> List<T> instanciarLista(List<T> lista) {
        if (lista == null) {
            return new ArrayList<T>();
        }
        return lista;
    }
    
    public static <T> Set<T> instanciarSet(Set<T> set) {
        if (set == null) {
            return new HashSet<T>();
        }
        return set;
    }
    
}
