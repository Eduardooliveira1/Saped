package br.gov.mme.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class EmailNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public EmailNotFoundException() {
        super(ErrorConstantsUtil.EMAIL_NOT_FOUND_TYPE, "Email address not registered", Status.BAD_REQUEST);
    }
}
