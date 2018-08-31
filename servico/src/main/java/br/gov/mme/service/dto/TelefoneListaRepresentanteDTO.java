package br.gov.mme.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class TelefoneListaRepresentanteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal ddd;
    private BigDecimal telefone;
    private Long idRepresentante;

    public TelefoneListaRepresentanteDTO() {
    }

    public TelefoneListaRepresentanteDTO(BigDecimal ddd,
                                         BigDecimal telefone,
                                         Long idRepresentante) {
        this.ddd = ddd;
        this.telefone = telefone;
        this.idRepresentante = idRepresentante;
    }

    public BigDecimal getDdd() {
        return ddd;
    }

    public void setDdd(BigDecimal ddd) {
        this.ddd = ddd;
    }

    public Long getIdRepresentante() {
        return idRepresentante;
    }

    public void setIdRepresentante(Long idRepresentante) {
        this.idRepresentante = idRepresentante;
    }

    public BigDecimal getTelefone() {
        return telefone;
    }

    public void setTelefone(BigDecimal telefone) {
        this.telefone = telefone;
    }

}
