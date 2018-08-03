package br.gov.mme.enumeration;

/**
 * Status do Boleto
 */
public enum TpBoleto {
    AV("AV"), AD("AD"), EM("EM"), PG("PG"), VE("VE"), NO("NO");

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
