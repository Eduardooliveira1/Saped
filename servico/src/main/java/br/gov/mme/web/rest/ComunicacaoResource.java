package br.gov.mme.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.ComunicacaoService;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;
import br.gov.mme.service.dto.NotificacaoCadastroDTO;
import br.gov.mme.web.rest.errors.BadRequestAlertException;
import br.gov.mme.web.rest.util.HeaderUtil;
import br.gov.mme.web.rest.util.PaginationUtil;

/**
 * REST controller for managing ComunicacaoResource.
 *
 * @see ComunicacaoService
 */
@RestController
@RequestMapping("/api/")
public class ComunicacaoResource {

    private final ComunicacaoService comunicacaoService;

    public static final String ENTITY_NAME = "comunicacao";

    public ComunicacaoResource(ComunicacaoService comunicacaoService, PessoaJuridicaService pessoaJuridicaService) {
        this.comunicacaoService = comunicacaoService;
    }


    @PostMapping("/comunicacao")
    @Timed
    public ResponseEntity<NotificacaoCadastroDTO> cadastrarNotificacao(@Valid @RequestBody NotificacaoCadastroDTO notificacao) throws URISyntaxException {

        if (notificacao.getId() != null) {
            throw new BadRequestAlertException("Um novo registro não pode ter um ID", ENTITY_NAME, "idexists");
        }

        NotificacaoCadastroDTO result = comunicacaoService.salvarNotificacao(notificacao);
        return ResponseEntity.created(new URI("/api/comunicacao/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    
    @GetMapping("/representantes")
    @Timed
    public ResponseEntity<Page<ComunicacaoRepresentantelistaDTO>> listarPessoasJuridicas(@RequestParam(value = "query", required = false) String query, Pageable pageable) {
        Page<ComunicacaoRepresentantelistaDTO> page = comunicacaoService.listarRepresentantes(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/representantes");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
    


 
}
