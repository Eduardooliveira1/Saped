package br.gov.mme.web.rest;

import br.gov.mme.service.BoletoService;
import br.gov.mme.service.CobrancaService;
import br.gov.mme.service.dto.CobrancaDTO;
import br.gov.mme.service.dto.DadosGerarBoletoDTO;
import com.codahale.metrics.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/")
public class BoletoResource {

    private final CobrancaService cobrancaService;

    public BoletoResource(
                          CobrancaService cobrancaService) {
        this.cobrancaService = cobrancaService;
    }

    @PostMapping("/gerar-boleto")
    @Timed
    public ResponseEntity<CobrancaDTO> gerarBoleto(@Valid @RequestBody DadosGerarBoletoDTO dadosDoBoleto) throws URISyntaxException {
        CobrancaDTO cobrancaGerada = cobrancaService.gerarBoleto(dadosDoBoleto);
        return new ResponseEntity<>(cobrancaGerada, HttpStatus.OK);
    }
}
