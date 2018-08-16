package br.gov.mme.nativequery;

import javax.persistence.Query;

import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

public class RelatorioPagamentoNativeQuery
        extends BaseNativeQuery<BoletoRelatorioPagamentoDTO, BoletoRelatorioPagamentoFiltroDTO> {

    public RelatorioPagamentoNativeQuery(BoletoRelatorioPagamentoFiltroDTO filtro) {
        super(new BoletoRelatorioPagamentoDTO(), filtro);
    }

    @Override
    public void appendSelectClause(StringBuilder queryBuilder) {
        queryBuilder.append("SELECT co_Cnpj cnpj, no_Fantasia nomeFantasia, vl_Boleto valorBoleto, ");
        queryBuilder.append("mm_Referencia mesReferencia, aa_Referencia anoReferencia, dt_Vencimento ");
        queryBuilder.append("dataVencimento, tp_Status statusBoleto ");
    }

    @Override
    public void appendFromClause(StringBuilder queryBuilder, Boolean isExporting) {
        queryBuilder.append("FROM tb_Status_Boleto st, tb_boleto b, tb_Pessoa_Juridica pj");
    }

    @Override
    public void addConditions(StringBuilder queryBuilder) {
        queryBuilder.append(" AND st.fk_Boleto = b.pk_Boleto");
        queryBuilder.append(" AND pj.fk_Pessoa_Juridica = b.fk_Pessoa_Juridica ");
        if (super.getFiltro() != null) {
            queryBuilder.append("AND (");
            this.setFiltroConditions(queryBuilder);
        }

    }

    private void setFiltroConditions(StringBuilder queryBuilder) {
        boolean checkOR = false;
        if (super.getFiltro().getValor() != null) {
            queryBuilder.append("vl_Boleto = ?1 ");
            checkOR = true;
        }
        if (super.getFiltro().getMesReferencia() != null) {
            checkOR = this.appendOR(checkOR, queryBuilder);
            queryBuilder.append("mm_Referencia = ?2 ");
        }
        if (super.getFiltro().getDataVencimento() != null) {
            checkOR = this.appendOR(checkOR, queryBuilder);
            queryBuilder.append("dt_Vencimento = ?3 ");
        }
        if (super.getFiltro().getTpStatusBoleto() != null) {
            this.appendOR(checkOR, queryBuilder);
            queryBuilder.append("tp_Status = ?4");
        }
        queryBuilder.append(")");
    }

    private boolean appendOR(boolean checkOR, StringBuilder queryBuilder) {
        if (checkOR) {
            queryBuilder.append("OR ");
        }
        return true;
    }

    @Override
    public void setFilterParameters(Query query) {
        if (super.getFiltro() != null) {
            if (super.getFiltro().getValor() != null) {
                setParameter(query, super.getFiltro().getValor(), 1);
            }
            if (super.getFiltro().getMesReferencia() != null) {
                setParameter(query, super.getFiltro().getMesReferencia(), 2);
            }
            if (super.getFiltro().getDataVencimento() != null) {
                setParameter(query, super.getFiltro().getDataVencimento(), 3);
            }
            if (super.getFiltro().getTpStatusBoleto() != null) {
                setParameter(query, super.getFiltro().getTpStatusBoleto().getId(), 4);
            }
        }
    }

}
