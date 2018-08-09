package br.gov.mme.enumeration;

import java.io.Serializable;

public enum FlNotificacao implements Serializable {
    N(Boolean.FALSE),S(Boolean.TRUE);
    private final Boolean flag;

    FlNotificacao(Boolean flag){
        this.flag = flag;
    }

    public Boolean get(){
        return flag;
    }
}
