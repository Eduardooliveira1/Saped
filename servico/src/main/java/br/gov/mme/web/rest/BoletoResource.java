package br.gov.mme.web.rest;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mee.vo.BoletoRelatorioPagamentoVO;
import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.BoletoService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.web.rest.util.HeaderUtil;

@RestController
@RequestMapping("/api/")
public class BoletoResource {

    private final BoletoService boletoService;

    private final Logger log = LoggerFactory.getLogger(BoletoResource.class);

    public static final String ENTITY_NAME = "boleto";

    public BoletoResource(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping("/boleto/export")
    @Timed
    public ResponseEntity<byte[]> cadastrarPessoaJuridica(
            @Valid @RequestBody BoletoRelatorioPagamentoFiltroDTO filtro) throws URISyntaxException {
        List<BoletoRelatorioPagamentoVO> dataRel = boletoService.converterFiltroToVO(filtro);

        try {
            return ResponseEntity.ok(boletoService.getRelatorio(dataRel));
        } catch (RelatorioException | LeituraBufferException | CheckedInvalidArgumentException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, e.getMessage()))
                    .body(null);
        }
    }

}
