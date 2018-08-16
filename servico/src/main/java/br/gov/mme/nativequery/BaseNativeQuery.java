package br.gov.mme.nativequery;

import javax.persistence.Query;

import br.com.basis.nativequerybuilder.pojo.NativeQueryBuilder;
import br.gov.mme.util.GenericTypeUtil;

public abstract class BaseNativeQuery<R, F> implements NativeQueryBuilder {

    private final F filtro;

    protected BaseNativeQuery(F filtro) {
        this.filtro = filtro;
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
        return new GenericTypeUtil<R>().getName();
    }
    
    public F getFiltro() {
        return filtro;
    }

}
