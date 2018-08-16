package br.gov.mme.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.basis.nativequerybuilder.pojo.NativeQueryBuilder;
import br.gov.mme.service.NativeQuerySAPEDService;
import br.gov.mme.web.rest.util.QueryUtil;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class NativeQuerySAPEDServiceImpl implements NativeQuerySAPEDService {

    private EntityManager entityManager;

    public NativeQuerySAPEDServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<?> filter(NativeQueryBuilder nativeQueryBuilder, Pageable pageable, Boolean isExporting) {
        Query query = getQuery(nativeQueryBuilder, pageable, isExporting);
        List<?> result = query.getResultList();
        return new PageImpl<>(result, pageable, 1L);
    }

    public static void setQueryParameters(Query query, Pageable pageable) {
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
    }

    private Query getQuery(NativeQueryBuilder nativeQueryBuilder, Pageable pageable, Boolean isExporting) {
        String queryString = getStringQuery(nativeQueryBuilder, pageable, isExporting);
        Query query = entityManager.createNativeQuery(queryString, nativeQueryBuilder
                .getSqlResultSetMapping());
        this.setQueryParameters(nativeQueryBuilder, query);
        return query;
    }

    private String getStringQuery(NativeQueryBuilder nativeQueryBuilder, Pageable pageable, Boolean isExporting) {
        StringBuilder queryBuilder = new StringBuilder();
        nativeQueryBuilder.appendSelectClause(queryBuilder);
        nativeQueryBuilder.appendFromClause(queryBuilder, isExporting);
        QueryUtil.addSort(pageable, queryBuilder);
        return queryBuilder.toString();
    }

    private void setQueryParameters(NativeQueryBuilder nativeQueryBuilder, Query query) {
        nativeQueryBuilder.setFilterParameters(query);
    }

}
