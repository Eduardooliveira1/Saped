package br.gov.mme.enumeration;

public enum EntityFields {
    CNPJ("cnpj");

    private String field;

    EntityFields(String field) {
        this.field = field;
    }

    public String field() {
        return this.field;
    }

}
