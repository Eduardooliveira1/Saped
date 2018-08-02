package br.gov.mme.exceptions;

import br.gov.mme.web.rest.PessoaJuridicaResource;

public class DeleteInexistentPJException extends DeleteInexistentEntityException {

    private static final long serialVersionUID = 1L;

    public DeleteInexistentPJException() {
        super(PessoaJuridicaResource.ENTITY_NAME);
    }

}
