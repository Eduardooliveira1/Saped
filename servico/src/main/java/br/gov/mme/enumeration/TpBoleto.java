package br.gov.mme.enumeration;

import java.io.Serializable;

public enum TpBoleto implements Serializable {
    NO("NO", "Normal"), SV("SV", "Segunda Via"), AD("AD", "Adiantamento");

    private final String status;

    private final String descricao;

    TpBoleto(String status, String descricao) {
        this.status = status;
        this.descricao = descricao;
    }

    public String status() {
        return this.status;
    }

    public String descricao() {
        return this.descricao;
    }

    @Override
    public String toString() {
        return this.status;
    }

}