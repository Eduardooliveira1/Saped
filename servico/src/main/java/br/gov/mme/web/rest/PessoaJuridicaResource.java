package br.gov.mme.web.rest;

import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.web.rest.errors.BadRequestAlertException;
import br.gov.mme.web.rest.util.HeaderUtil;
import br.gov.mme.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for managing PessoaJuridicaResource.
 *
 * @see PessoaJuridicaService
 */
@RestController
@RequestMapping("/api/")
public class PessoaJuridicaResource {

    private final PessoaJuridicaService pessoaJuridicaService;

    public static final String ENTITY_NAME = "pessoa-juridica";

    public PessoaJuridicaResource(PessoaJuridicaService pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @GetMapping("/pessoas-juridicas")
    @Timed
    public ResponseEntity<Page<PessoaJuridicaListaDTO>> listarPessoasJuridicas(@RequestParam(value = "query", required = false) String query, Pageable pageable) {
        Page<PessoaJuridicaListaDTO> page = pessoaJuridicaService.listarPessoasJuridicas(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pessoas-juridicas");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    @GetMapping("/pessoa-juridica/{id}")
    @Timed
    public ResponseEntity<PessoaJuridicaCadastroDTO> obterPessoaJuridica(@PathVariable("id") Long id) throws URISyntaxException {
        PessoaJuridicaCadastroDTO result = pessoaJuridicaService.obterPordId(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/pessoa-juridica")
    @Timed
    public ResponseEntity<PessoaJuridicaCadastroDTO> cadastrarPessoaJuridica(@Valid @RequestBody PessoaJuridicaCadastroDTO pessoaJuridica) throws URISyntaxException {

        if (pessoaJuridica.getId() != null) {
            throw new BadRequestAlertException("Um novo registro não pode ter um ID", ENTITY_NAME, "idexists");
        }

        PessoaJuridicaCadastroDTO result = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);
        return ResponseEntity.created(new URI("/api/dirigentes/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/pessoa-juridica")
    @Timed
    public ResponseEntity<PessoaJuridicaCadastroDTO> atualizarPessoaJuridica(@Valid @RequestBody PessoaJuridicaCadastroDTO pessoaJuridica) throws URISyntaxException {

        if (pessoaJuridica.getId() == null) {
            return cadastrarPessoaJuridica(pessoaJuridica);
        }

        PessoaJuridicaCadastroDTO result = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/pessoa-juridica/{id}")
    @Timed
    public ResponseEntity<PessoaJuridicaListaDTO> removerPessoaJuridica(
            @PathVariable("id") Long id) {
        pessoaJuridicaService.excluirPessoaJuridica(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
