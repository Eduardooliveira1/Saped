package br.gov.mme.web.rest.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

public final class QueryUtil {

    private QueryUtil() {

    }

    public static String preparaStringLike(String texto) {
        StringBuilder sb = new StringBuilder("%").append(texto).append("%");
        return sb.toString();
    }

    public static void addSort(Pageable pageable, StringBuilder queryBuilder) {
        if (pageable.getSort() != null && pageable.getSort().iterator().hasNext()) {
            queryBuilder.append(" ORDER BY ");
            Order order = pageable.getSort().iterator().next();
            queryBuilder.append(order.getProperty());
            queryBuilder.append(" ");
            queryBuilder.append(order.getDirection());
        }
    }
}
