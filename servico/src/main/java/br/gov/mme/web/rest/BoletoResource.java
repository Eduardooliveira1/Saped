package br.gov.mme.web.rest;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mee.vo.BoletoRelatorioPagamentoVO;
import br.gov.mme.service.BoletoService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;

@RestController
@RequestMapping("/api/")
public class BoletoResource {

    private final BoletoService boletoService;

    public BoletoResource(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping("/boleto/export")
    @Timed
    public ResponseEntity<byte[]> cadastrarPessoaJuridica(
            @Valid @RequestBody BoletoRelatorioPagamentoFiltroDTO filtro) throws URISyntaxException {
        List<BoletoRelatorioPagamentoVO> dataRel = boletoService.converterFiltroToVO(filtro);
        return ResponseEntity.accepted().body(boletoService.getRelatorio(dataRel).toByteArray());
    }

}
