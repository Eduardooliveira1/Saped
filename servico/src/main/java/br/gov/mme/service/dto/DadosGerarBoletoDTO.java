package br.gov.mme.service.dto;

import br.gov.mme.enumeration.AcaoGerarBoleto;
import br.gov.mme.enumeration.TpStatusBoleto;

import java.io.Serializable;
import java.math.BigDecimal;

public class DadosGerarBoletoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idPj;
    private String dataVencimento;
    private Long valor;

    public DadosGerarBoletoDTO() {
    }

    public DadosGerarBoletoDTO(Long idPj,
                               String dataVencimento,
                               Long valor) {
        this.idPj = idPj;
        this.dataVencimento =  dataVencimento;
        this.valor =  valor;
    }

    public Long getIdPj() {
        return idPj;
    }

    public void setIdPj(Long idPj) {
        this.idPj = idPj;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
}
