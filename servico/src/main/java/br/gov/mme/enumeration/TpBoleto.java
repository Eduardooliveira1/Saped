package br.gov.mme.enumeration;

/**
 * Status do Boleto
 */
public enum TpBoleto {
    NO("NO"), SV("SV"), AD("AD");

    private final String status;

    private TpBoleto(String status) {
        this.status = status;
    }

    public String status() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.status;
    }

}
