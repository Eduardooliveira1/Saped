package br.gov.mme.service.filter;

import br.gov.mme.domain.PessoaJuridica_;
import br.gov.mme.enumerator.FlStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaFilter implements EntityFilter {

    private String filtro;

    @Override
    public Specification filter() {

        return (root, cq, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            Predicate predicateStatus = cb.equal(root.get(PessoaJuridica_.pessoa).get("status"), FlStatus.S);
            predicates.add(predicateStatus);

            if(StringUtils.isNotBlank(filtro)){
                Predicate predicateCnpj = cb.or( cb.like(cb.lower(root.get(PessoaJuridica_.cnpj)), "%" + this.filtro.toLowerCase() + "%"));
                predicates.add(predicateCnpj);

                Predicate predicateSigla = cb.or( cb.like(cb.lower(root.get(PessoaJuridica_.sigla)), "%" + this.filtro.toLowerCase() + "%"));
                predicates.add(predicateSigla);

                Predicate predicateNomeFantasia = cb.or( cb.like(cb.lower(root.get(PessoaJuridica_.nomeFantasia)), "%" + this.filtro.toLowerCase() + "%"));
                predicates.add(predicateNomeFantasia);

                Predicate predicateRzaoSocial= cb.or( cb.like(cb.lower(root.get(PessoaJuridica_.razaoSocial)), "%" + this.filtro.toLowerCase() + "%"));
                predicates.add(predicateRzaoSocial);

            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
}
