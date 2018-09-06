package br.gov.mme.nativequery;

import br.com.basis.nativequerybuilder.pojo.NativeQueryBuilder;
import br.gov.mme.service.dto.CobrancaBoletoDTO;
import br.gov.mme.service.dto.FiltroListagemCobrancaDTO;

import javax.persistence.Query;

public class CobrancaBoletoNativeQuery  implements NativeQueryBuilder {

    public static final String CLASS_NAME = CobrancaBoletoDTO.class.getSimpleName();
    private final FiltroListagemCobrancaDTO filtro;

    public CobrancaBoletoNativeQuery(FiltroListagemCobrancaDTO filtro) {
        this.filtro = filtro;
    }

    @Override
    public void appendSelectClause(StringBuilder queryBuilder) {
        queryBuilder.append("SELECT b.dt_Vencimento AS dataVencimento, ");
        queryBuilder.append(       "( CASE ");
        queryBuilder.append(           "WHEN tp_status = 'PG' THEN stb.dh_Status ");
        queryBuilder.append(           "ELSE NULL ");
        queryBuilder.append(           "END )       AS dataPagamento, ");
        queryBuilder.append(       "( CASE ");
        queryBuilder.append(           "WHEN tp_boleto = 'SV' THEN b.dt_Vencimento ");
        queryBuilder.append(           "ELSE NULL ");
        queryBuilder.append(         "END )       AS dataSegundaVia, ");
        queryBuilder.append(       "b.vl_Boleto     AS valorBoleto, ");
        queryBuilder.append(       "pj.co_Cnpj       AS cnpj, ");
        queryBuilder.append(       "stb.tp_Status     statusBoleto, ");
        queryBuilder.append(       "b.mm_Referencia mesReferencia, ");
        queryBuilder.append(       "b.aa_Referencia anoReferencia ");

    }

    @Override
    public void appendFromClause(StringBuilder queryBuilder, Boolean aBoolean) {
        queryBuilder.append("FROM   tb_boleto b, ");
        queryBuilder.append(       "tb_pessoa_juridica pj, ");
        queryBuilder.append(       "tb_status_boleto stb ");
    }

    @Override
    public void addConditions(StringBuilder queryBuilder) {
        queryBuilder.append("AND b.aa_referencia = ?1 ");
        queryBuilder.append("AND pj.fk_pessoa_juridica = ?2 ");
        queryBuilder.append("AND pj.fk_pessoa_juridica = b.fk_pessoa_juridica ");
        queryBuilder.append("AND stb.fk_boleto = b.pk_boleto ");
    }

    @Override
    public void setFilterParameters(Query query) {
        query.setParameter(1, this.filtro.getAno());
        query.setParameter(2, this.filtro.getIdPj());

    }

    @Override
    public String getSqlResultSetMapping() {
        return CLASS_NAME;
    }

    @Override
    public void appendExportingFromClause(StringBuilder stringBuilder) {

    }

    @Override
    public void appendExportingSelectClause(StringBuilder stringBuilder) {

    }

}
