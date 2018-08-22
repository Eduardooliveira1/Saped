package br.gov.mme.service.util;

import java.text.DateFormatSymbols;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtils {

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    private DateUtils() {
    }

    public static String convertMesReferenciaToString(Integer mesReferencia) {
        Locale br = new Locale("pt", "BR");
        try {
            return new DateFormatSymbols(br).getMonths()[mesReferencia + 1];
        } catch (java.util.MissingResourceException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

    public static String convertMesAnoReferenciaToString(Integer mesReferencia, Integer anoReferencia) {
        return convertMesReferenciaToString(mesReferencia) + "/" + anoReferencia;
    }

    public static String convertDateTimeToDate(String data) {
        return data.substring(0, 10);
    }

}
