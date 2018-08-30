package br.gov.mme.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SapedUtil {

    private SapedUtil() { }

    public static <T> List<T> instanciarLista(List<T> lista) {
        return lista == null ? new ArrayList<T>() : lista;
    }
    
    public static <T> Set<T> instanciarSet(Set<T> set) {
        return set == null ? new HashSet<T>() : set;
    }
    
}
