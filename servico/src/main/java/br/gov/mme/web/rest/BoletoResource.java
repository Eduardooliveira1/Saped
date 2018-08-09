package br.gov.mme.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mme.service.BoletoService;

@RestController
@RequestMapping("/api/")
public class BoletoResource {

    private final BoletoService boletoService;

    public static final String ENTITY_NAME = "boleto";

    public BoletoResource(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

}
