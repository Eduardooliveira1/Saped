package br.gov.mme.exceptions;

import br.gov.mme.enumeration.EntityFields;
import br.gov.mme.web.rest.PessoaJuridicaResource;

public class CnpjInvalidoException extends InvalidFieldException {

    private static final long serialVersionUID = 1L;

    public CnpjInvalidoException() {
        super(PessoaJuridicaResource.ENTITY_NAME, EntityFields.CNPJ.field());
    }

}
