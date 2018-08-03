package br.gov.mme.enumeration;

/**
 * Status do Boleto
 */
public enum TpStatusBoleto {
    AV("AV"), AD("AD"), EM("EM"), PG("PG"), VE("VE");

    private final String status;

    private TpStatusBoleto(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }

}
