package br.gov.mme.utils;

public final class ExceptionUtils {

    private ExceptionUtils() {
    }
    
    public static GenericException convertToGeneric(Exception exception) {
        return new GenericException(exception);
    }
}
