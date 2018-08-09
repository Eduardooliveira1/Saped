package br.gov.mme.enumeration;

public enum FlNotificacao {
    N(Boolean.FALSE),S(Boolean.TRUE);
    private final Boolean flag;

    FlNotificacao(Boolean flag){
        this.flag = flag;
    }

    public Boolean get(){
        return flag;
    }
}
