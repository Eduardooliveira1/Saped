package br.gov.mme.web.rest.util;

public class QueryUtil {

    public static String preparaStringLike(String texto){
        return "%"+texto+"%";
    }
}
