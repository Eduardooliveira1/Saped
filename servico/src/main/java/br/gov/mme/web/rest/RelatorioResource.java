package br.gov.mme.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.exceptions.CheckedInvalidArgumentException;
import br.gov.mme.exceptions.LeituraBufferException;
import br.gov.mme.exceptions.RelatorioException;
import br.gov.mme.service.RelatorioService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.dto.ListaNomePessoaJuridicaDTO;
import br.gov.mme.web.rest.util.HeaderUtil;
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

    private final Logger log = LoggerFactory.getLogger(RelatorioResource.class);

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

    @PostMapping("/pagamentos/exportar")
    @Timed
    public ResponseEntity<byte[]> exportarPagamentos(@Valid @RequestBody BoletoRelatorioPagamentoFiltroDTO filtro)
            throws URISyntaxException {
        List<BoletoRelatorioPagamentoDTO> dataRel = relatorioService.converterFiltroToVO(filtro);

        try {
            return ResponseEntity.ok(relatorioService.getRelatorio(dataRel));
        } catch (RelatorioException | LeituraBufferException | CheckedInvalidArgumentException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil.createFailureAlert(BoletoResource.ENTITY_NAME, e.getMessage()))
                    .body(null);
        }
    }

    @GetMapping("/pessoas-juridicas")
    @Timed
    public ResponseEntity<Set<ListaNomePessoaJuridicaDTO>> listarNomesPessoasJuridicas() {
        Set<ListaNomePessoaJuridicaDTO> nomes = relatorioService.getNomesPessoasJuridicas();
        return new ResponseEntity<>(nomes, HttpStatus.OK);
    }

}
