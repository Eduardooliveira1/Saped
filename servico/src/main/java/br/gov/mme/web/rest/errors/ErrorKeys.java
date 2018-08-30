package br.gov.mme.web.rest.errors;

public enum ErrorKeys {
    ID_INEXISTENT("idnotexists"), ID_EXISTS("idexists"), CNPJ_EXISTS("cnpjexists"), 
    CNPJ_INVALID("cnpjinvalid");

    private final String error;

    ErrorKeys(String error) {
        this.error = error;
    }

    public String error() {
        return error;
    }

}
