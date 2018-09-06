package br.gov.mme.service.dto;

import java.io.Serializable;

public class FiltroListagemCobrancaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private long ano;
    private long idPj;



    public FiltroListagemCobrancaDTO() { }

    public FiltroListagemCobrancaDTO(long ano, long idPj ) {
        this.ano = ano;
        this.idPj = idPj;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public Long getIdPj() {
        return idPj;
    }

    public void setIdPj(Long idPj) {
        this.idPj = idPj;
    }
}
