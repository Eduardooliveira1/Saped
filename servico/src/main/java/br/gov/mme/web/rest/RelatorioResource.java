package br.gov.mme.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.RelatorioService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.web.rest.util.PaginationUtil;



/**
 * REST controller for managing Resource.
 *
 * @see RelatorioService
 */
@RestController
@RequestMapping("/api/relatorios/")
public class RelatorioResource {

    private final RelatorioService relatorioService;

    public RelatorioResource(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/pagamentos")
    @Timed
    public ResponseEntity<Page<BoletoRelatorioPagamentoDTO>> listarPagamentos(Pageable pageable) {
        Page<BoletoRelatorioPagamentoDTO> page = this.relatorioService.listarPagamentos(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/relatorios/pagamentos");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

}
