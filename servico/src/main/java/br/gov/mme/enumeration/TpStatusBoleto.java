package br.gov.mme.enumeration;

/**
 * Status do Boleto
 */
public enum StatusPagamento {
    P("Pago"), V("Vencido"), A_V("À vencer"), AD("Adiantado");

    private final String status;

    private StatusPagamento(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }

}
