package br.gov.mme.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.gov.mme.domain.User;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends ElasticsearchRepository<User, Long> {
}
