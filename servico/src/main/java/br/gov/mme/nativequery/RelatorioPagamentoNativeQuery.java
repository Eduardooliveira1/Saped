package br.gov.mme.nativequery;

import javax.persistence.Query;

import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.util.DateUtils;

public class RelatorioPagamentoNativeQuery
        extends BaseNativeQuery<BoletoRelatorioPagamentoFiltroDTO> {
    
    public static final String CLASS_NAME = BoletoRelatorioPagamentoDTO.class.getSimpleName();

    public RelatorioPagamentoNativeQuery(BoletoRelatorioPagamentoFiltroDTO filtro) {
        super(CLASS_NAME, filtro, false);
    }

    public RelatorioPagamentoNativeQuery() {
        this(null);
    }

    @Override
    public void appendSelectClause(StringBuilder queryBuilder) {
        queryBuilder.append("SELECT co_Cnpj cnpj, no_Fantasia nomeFantasia, vl_Boleto valorBoleto, ");
        queryBuilder.append("mm_Referencia mesReferencia, aa_Referencia anoReferencia, dt_Vencimento ");
        queryBuilder.append("dataVencimento, tp_Status statusBoleto ");
    }

    @Override
    public void appendFromClause(StringBuilder queryBuilder, Boolean isExporting) {
        queryBuilder.append("FROM tb_Status_Boleto st, tb_boleto b, tb_Pessoa_Juridica pj ");
    }

    @Override
    public void addConditions(StringBuilder queryBuilder) {
        super.addConditions(queryBuilder);
        queryBuilder.append("st.fk_Boleto = b.pk_Boleto ");
        queryBuilder.append("AND pj.fk_Pessoa_Juridica = b.fk_Pessoa_Juridica ");
        if (super.getFiltro() != null) {
            queryBuilder.append("AND (");
            this.setFiltroConditions(queryBuilder);
            queryBuilder.append(")");
        }

    }

    private void setFiltroConditions(StringBuilder queryBuilder) {
        if (super.haveFilterAttr(super.getFiltro().getValor(), queryBuilder)) {
            queryBuilder.append("vl_Boleto = ?1 ");
        }
        if (super.haveFilterAttr(super.getFiltro().getMesReferencia(), queryBuilder)) {
            queryBuilder.append("mm_Referencia = ?2 ");
        }
        if (super.haveFilterAttr(super.getFiltro().getDataVencimento(), queryBuilder)) {
            queryBuilder.append("dt_Vencimento = ?3 ");
        }
        if (super.haveFilterAttr(super.getFiltro().getTpStatusBoleto(), queryBuilder)) {
            queryBuilder.append("tp_Status = ?4 ");
        }
        if (super.haveFilterAttr(super.getFiltro().getIdsPessoasJuridicas(), queryBuilder)) {
            queryBuilder.append("b.fk_Pessoa_Juridica in ( ?5 ) ");
        }
    }

    @Override
    public void setFilterParameters(Query query) {
        if (super.getFiltro() != null) {
            if (super.isNotNull(super.getFiltro().getValor())) {
                setParameter(query, super.getFiltro().getValor(), 1);
            }
            if (super.isNotNull(super.getFiltro().getMesReferencia())) {
                setParameter(query, super.getFiltro().getMesReferencia(), 2);
            }
            if (super.isNotNull(super.getFiltro().getDataVencimento())) {
                setParameter(query, DateUtils.convertDateTimeToDate(super.getFiltro().getDataVencimento()), 3);
            }
            if (super.isNotNull(super.getFiltro().getTpStatusBoleto())) {
                setParameter(query, super.getFiltro().getTpStatusBoleto(), 4);
            }
            if (super.isNotEmpty(super.getFiltro().getIdsPessoasJuridicas())) {
                setParameter(query, super.getFiltro().getIdsPessoasJuridicas(), 5);
            }
        }
    }

}
