package br.gov.mme.nativequery;

import java.util.Collection;

import javax.persistence.Query;

import br.com.basis.nativequerybuilder.pojo.NativeQueryBuilder;

public abstract class BaseNativeQuery<F> implements NativeQueryBuilder {

    private final F filtro;

    private final String className;

    private boolean checkOr;

    protected BaseNativeQuery(String className, F filtro, boolean checkOr) {
        this.filtro = filtro;
        this.className = className;
        this.checkOr = checkOr;
    }

    @Override
    public void appendExportingFromClause(StringBuilder queryBuilder) {
    }

    @Override
    public void appendExportingSelectClause(StringBuilder queryBuilder) {
    }

    @Override
    public void addConditions(StringBuilder queryBuilder) {
        queryBuilder.append("WHERE ");
    }

    protected void setParameter(Query query, Object param, int pos) {
        query.setParameter(pos, param);
    }

    @Override
    public String getSqlResultSetMapping() {
        return className;
    }
    
    public F getFiltro() {
        return filtro;
    }

    protected boolean haveFilterAttr(Object attr, StringBuilder queryBuilder) {
        if (isNotNull(attr)) {
            this.appendOr(queryBuilder);
            return true;
        }
        return false;
    }

    protected boolean haveFilterAttr(Collection<?> attr, StringBuilder queryBuilder) {
        if (isNotEmpty(attr)) {
            this.appendOr(queryBuilder);
            return true;
        }
        return false;
    }

    protected boolean isNotNull(Object obj) {
        return (!(obj == null));
    }

    protected boolean isNotEmpty(Collection<?> col) {
        return (!(isNotNull(col) && col.isEmpty()));
    }

    private void appendOr(StringBuilder queryBuilder) {
        if (checkOr) {
            queryBuilder.append("OR ");
            return;
        }
        this.checkOr = true;
    }

    public boolean isCheckOr() {
        return checkOr;
    }

}
