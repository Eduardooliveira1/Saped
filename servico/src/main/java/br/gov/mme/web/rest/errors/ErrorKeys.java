package br.gov.mme.web.rest.errors;

public enum ErrorKeys {
    ID_INEXISTENT("idnotexists"), ID_EXISTS("idexists"), CNPJ_EXISTS("cnpjexiste"), 
    CNPJ_INVALID("cnpjinvalido");

    private final String error;

    ErrorKeys(String error) {
        this.error = error;
    }

    public String error() {
        return error;
    }

}
