package br.gov.mme.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mme.service.BoletoService;

@RestController
@RequestMapping("/api/")
public class BoletoResource {

    public static final String ENTITY_NAME = "boleto";

    private final BoletoService boletoService;

    public BoletoResource(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

}
