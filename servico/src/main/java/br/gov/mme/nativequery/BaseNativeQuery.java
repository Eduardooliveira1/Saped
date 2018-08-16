package br.gov.mme.nativequery;

import javax.persistence.Query;

import br.com.basis.nativequerybuilder.pojo.NativeQueryBuilder;

public abstract class BaseNativeQuery<D, F> implements NativeQueryBuilder {

    private final F filtro;

    private final D dto;

    protected BaseNativeQuery(D dto, F filtro) {
        this.filtro = filtro;
        this.dto = dto;
    }

    @Override
    public void appendExportingFromClause(StringBuilder queryBuilder) {
    }

    @Override
    public void appendExportingSelectClause(StringBuilder queryBuilder) {
    }

    protected void setParameter(Query query, Object param, int pos) {
        query.setParameter(pos, param);
    }

    @Override
    public String getSqlResultSetMapping() {
        Class<? extends Object> clazz = dto.getClass();
        return clazz.getSimpleName();
    }
    
    public F getFiltro() {
        return filtro;
    }

}
