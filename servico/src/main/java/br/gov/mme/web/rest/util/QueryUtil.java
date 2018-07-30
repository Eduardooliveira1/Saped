package br.gov.mme.web.rest.util;

public final class QueryUtil {

    private QueryUtil() {

    }

    public static String preparaStringLike(String texto) {
        StringBuilder sb = new StringBuilder("%").append(texto).append("%");
        return sb.toString();
    }
}
