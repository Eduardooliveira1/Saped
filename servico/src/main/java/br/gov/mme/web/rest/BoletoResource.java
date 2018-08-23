package br.gov.mme.web.rest;

import br.gov.mme.service.BoletoService;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.CobrancaDTO;
import com.codahale.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;

/**
 * REST controller for managing PessoaJuridicaResource.
 *
 * @see PessoaJuridicaService
 */
@RestController
@RequestMapping("/api/")
public class BoletoResource {

    private final BoletoService boletoService;

    public BoletoResource(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping("/boleto/gerar-boleto")
    @Timed
    public CobrancaDTO gerarBoleto(
            //TODO::Ajustar esta função e ver o que será de fato retornado.
            @Valid @RequestBody CobrancaDTO cobranca) throws URISyntaxException {
            boletoService.gerarBoleto(cobranca);
            CobrancaDTO  result = new CobrancaDTO();
            return result;
    }
}
