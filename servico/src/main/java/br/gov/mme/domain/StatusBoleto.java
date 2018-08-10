package br.gov.mme.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.gov.mme.enumeration.FlSituacaoBancaria;
import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.enumeration.converter.FlSituacaoBancariaConverter;
import br.gov.mme.util.SapedUtil;

@Entity
@Table(name = "tb_Status_Boleto")
public class StatusBoleto implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "pk_Status_Boleto")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Boleto> boletoId;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_Status")
    @NotNull
    private TpStatusBoleto tpStatusBoleto;

    @Column(name = "dh_Status")
    private LocalDateTime dataHoraStatus;

    @Column(name = "fl_Situacao_Bancaria")
    @Convert(converter = FlSituacaoBancariaConverter.class)
    private FlSituacaoBancaria situacaoBancaria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Boleto> getBoletoId() {
        return SapedUtil.instanciarLista(boletoId);
    }

    public void setBoletoId(List<Boleto> boletoId) {
        this.boletoId = SapedUtil.instanciarLista(boletoId);
    }

    public TpStatusBoleto getTpStatusBoleto() {
        return tpStatusBoleto;
    }

    public void setTpStatusBoleto(TpStatusBoleto tpStatusBoleto) {
        this.tpStatusBoleto = tpStatusBoleto;
    }

    public LocalDateTime getDataHoraStatus() {
        return dataHoraStatus;
    }

    public void setDataHoraStatus(LocalDateTime dataHoraStatus) {
        this.dataHoraStatus = dataHoraStatus;
    }

    public FlSituacaoBancaria getSituacaoBancaria() {
        return situacaoBancaria;
    }

    public void setSituacaoBancaria(FlSituacaoBancaria situacaoBancaria) {
        this.situacaoBancaria = situacaoBancaria;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
