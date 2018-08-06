package br.gov.mme.service.dto;

import br.gov.mme.enumeration.FlStatus;

import java.math.BigDecimal;

public class TelefoneDTO {

    private Long id;
    private BigDecimal ddd;
    private BigDecimal telefone;
    private FlStatus status;

    public TelefoneDTO() {
    }

    public TelefoneDTO(BigDecimal ddd,
                       BigDecimal telefone,
                       FlStatus status,
                       Long id) {

        this.ddd = ddd;
        this.telefone = telefone;
        this.status = status;
        this.id = id;
    }

    public BigDecimal getDdd() {
        return ddd;
    }

    public void setDdd(BigDecimal ddd) {
        this.ddd = ddd;
    }

    public BigDecimal getTelefone() {
        return telefone;
    }

    public void setTelefone(BigDecimal telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FlStatus getStatus() {
        return status;
    }

    public void setStatus(FlStatus status) {
        this.status = status;
    }

}
