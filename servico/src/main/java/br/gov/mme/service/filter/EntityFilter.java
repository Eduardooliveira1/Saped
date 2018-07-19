package br.gov.mme.service.filter;

import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("rawtypes")
public interface EntityFilter {

    Specification filter();
}
