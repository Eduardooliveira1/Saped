package br.gov.mme.util;

import java.util.ArrayList;
import java.util.List;

public class SapedUtil {

    public static <T> List<T> instanciarLista(List<T> list) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        return list;
    }

}
