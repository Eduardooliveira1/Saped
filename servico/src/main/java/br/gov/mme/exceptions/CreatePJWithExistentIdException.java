package br.gov.mme.exceptions;

import br.gov.mme.web.rest.PessoaJuridicaResource;

public class CreatePJWithExistentIdException extends CreateEntityWithExistentIdException {

    private static final long serialVersionUID = 1L;

    public CreatePJWithExistentIdException() {
        super(PessoaJuridicaResource.ENTITY_NAME);
    }

}
