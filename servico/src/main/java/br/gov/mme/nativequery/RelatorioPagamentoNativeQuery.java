package br.gov.mme.nativequery;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;

import br.com.basis.nativequerybuilder.pojo.NativeQueryBuilder;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.util.DateUtils;

public class RelatorioPagamentoNativeQuery
        implements NativeQueryBuilder {
    
    public static final String CLASS_NAME = BoletoRelatorioPagamentoDTO.class.getSimpleName();

    private final BoletoRelatorioPagamentoFiltroDTO filtro;

    public RelatorioPagamentoNativeQuery(BoletoRelatorioPagamentoFiltroDTO filtro) {
        this.filtro = filtro;
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
        queryBuilder.append("AND st.fk_Boleto = b.pk_Boleto ");
        queryBuilder.append("AND pj.fk_Pessoa_Juridica = b.fk_Pessoa_Juridica ");
        if (this.filtro != null) {
            this.setFiltroConditions(queryBuilder);
        }

    }

    private void setFiltroConditions(StringBuilder queryBuilder) {
        if (this.filtro.getValor() != null) {
            queryBuilder.append("AND vl_Boleto = ?1 ");
        }
        if (this.filtro.getMesReferencia() != null) {
            queryBuilder.append("AND mm_Referencia = ?2 ");
        }
        if (this.filtro.getDataVencimento() != null) {
            queryBuilder.append("AND dt_Vencimento = ?3 ");
        }
        if (this.filtro.getTpStatusBoleto() != null) {
            queryBuilder.append("AND tp_Status = ?4 ");
        }
        if (!CollectionUtils.isEmpty(this.filtro.getIdsPessoasJuridicas())) {
            queryBuilder.append("AND b.fk_Pessoa_Juridica in ( ?5 ) ");
        }
    }

    @Override
    public void setFilterParameters(Query query) {
        if (this.filtro != null) {
            if (this.filtro.getValor() != null) {
                setParameter(query, this.filtro.getValor(), 1);
            }
            if (this.filtro.getMesReferencia() != null) {
                setParameter(query, this.filtro.getMesReferencia(), 2);
            }
            if (this.filtro.getDataVencimento() != null) {
                setParameter(query, DateUtils.convertDateTimeToDate(this.filtro.getDataVencimento()), 3);
            }
            if (this.filtro.getTpStatusBoleto() != null) {
                setParameter(query, this.filtro.getTpStatusBoleto(), 4);
            }
            if (!CollectionUtils.isEmpty(this.filtro.getIdsPessoasJuridicas())) {
                setParameter(query, this.filtro.getIdsPessoasJuridicas(), 5);
            }
        }
    }

    private void setParameter(Query query, Object param, int pos) {
        query.setParameter(pos, param);
    }

    @Override
    public void appendExportingSelectClause(StringBuilder queryBuilder) {
        return;
    }

    @Override
    public void appendExportingFromClause(StringBuilder queryBuilder) {
        return;
    }

    @Override
    public String getSqlResultSetMapping() {
        return CLASS_NAME;
    }

}
