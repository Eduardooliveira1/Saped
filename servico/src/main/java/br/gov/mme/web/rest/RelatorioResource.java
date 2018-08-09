package br.gov.mme.web.rest;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.exceptions.ArquivoDeTipoInvalidoException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.BoletoService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Resource.
 *
 * @see RelatorioService
 */
@RestController
@RequestMapping("/api/relatorios/")
public class RelatorioResource {

    private final BoletoService boletoService;

    private final Logger log = LoggerFactory.getLogger(RelatorioResource.class);

    public RelatorioResource(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @PostMapping("/pagamentos/exportar")
    @Timed
    public ResponseEntity<?> exportarPagamentos(@Valid @RequestBody BoletoRelatorioPagamentoFiltroDTO filtro,
            HttpServletResponse response)
            throws URISyntaxException {
        try {
            boletoService.getRelatorio(filtro, response);
            return ResponseEntity.ok().build();
        } catch (RelatorioException | LeituraBufferException | ArquivoDeTipoInvalidoException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(BoletoResource.ENTITY_NAME, e.getMessage())).body(null);
        }
    }

}
