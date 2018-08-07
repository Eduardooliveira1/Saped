package br.gov.mme.service.util;

import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtils {

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    private DateUtils() {

    }

    public static String convertMesReferenciaToString(BigInteger mesReferencia) {
        final int mesRef = mesReferencia.intValue();
        Locale br = new Locale("pt", "BR");
        try {
            return new DateFormatSymbols(br).getMonths()[mesRef - 1];
        } catch (java.util.MissingResourceException e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }

}
