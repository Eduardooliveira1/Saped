package br.gov.mme.web.rest;

import java.io.ByteArrayOutputStream;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

@RestController
@RequestMapping("/api/")
public class BoletoResource {

    @PostMapping("/boleto/export")
    @Timed
    public ResponseEntity<ByteArrayOutputStream> cadastrarPessoaJuridica(
            @Valid @RequestBody BoletoRelatorioPagamentoFiltroDTO filtro) throws URISyntaxException {
        return null;
    }

}
