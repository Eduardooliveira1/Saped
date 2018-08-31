package br.gov.mme.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.ComunicacaoService;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.NotificacaoCadastroDTO;
import br.gov.mme.web.rest.errors.BadRequestAlertException;
import br.gov.mme.web.rest.util.HeaderUtil;

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

    public ComunicacaoResource(ComunicacaoService comunicacaoService,
                               PessoaJuridicaService pessoaJuridicaService) {
        this.comunicacaoService = comunicacaoService;
    }

    @PostMapping("/comunicacao")
    @Timed
    public ResponseEntity<NotificacaoCadastroDTO> cadastrarNotificacao(
        @Valid @RequestBody NotificacaoCadastroDTO notificacao) throws URISyntaxException {

        if (notificacao.getId() != null) {
            throw new BadRequestAlertException("Um novo registro não pode ter um ID", ENTITY_NAME, "idexists");
        }

        NotificacaoCadastroDTO result = comunicacaoService.salvarNotificacao(notificacao);
        return ResponseEntity.created(new URI("/api/comunicacao/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId()
                .toString()))
            .body(result);
    }
}