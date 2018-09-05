package br.gov.mme.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.exceptions.CNPJInvalidoException;
import br.gov.mme.exceptions.CreateEntityWithExistentIdException;
import br.gov.mme.exceptions.DeleteInexistentEntityException;
import br.gov.mme.exceptions.NullCPNJException;
import br.gov.mme.service.EnumerationService;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.CredenciaisNovaSenhaDTO;
import br.gov.mme.service.dto.EnumerationDTO;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.dto.PessoaJuridicaComboDTO;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.service.dto.PessoaJuridicaNomeDTO;
import br.gov.mme.web.rest.util.HeaderUtil;
import br.gov.mme.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing PessoaJuridicaResource.
 *
 * @see PessoaJuridicaService
 */
@RestController
@RequestMapping("/api/")
public class PessoaJuridicaResource {

    private final PessoaJuridicaService pessoaJuridicaService;

    private final EnumerationService enumerationService;

    public static final String ENTITY_NAME = "pessoa-juridica";

    private final Logger log = LoggerFactory.getLogger(PessoaJuridicaResource.class);

    public PessoaJuridicaResource(PessoaJuridicaService pessoaJuridicaService, EnumerationService enumerationService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
        this.enumerationService = enumerationService;
    }

    @GetMapping("/pessoas-juridicas")
    @Timed
    public ResponseEntity<Page<PessoaJuridicaListaDTO>> listarPessoasJuridicas(@RequestParam(value = "query", required = false) String query, Pageable pageable) {
        Page<PessoaJuridicaListaDTO> page = pessoaJuridicaService.listarPessoasJuridicas(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pessoas-juridicas");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    @GetMapping("/pessoas-juridicas/todas")
    @Timed
    public ResponseEntity<List<PessoaJuridicaComboDTO>> listarTodas() {
        List<PessoaJuridicaComboDTO> result = pessoaJuridicaService.listarTodas();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/pessoa-juridica/{id}")
    @Timed
    public ResponseEntity<PessoaJuridicaCadastroDTO> obterPessoaJuridica(@PathVariable("id") Long id) throws URISyntaxException {
        PessoaJuridicaCadastroDTO result = pessoaJuridicaService.obterPordId(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/pessoa-juridica")
    @Timed
    public ResponseEntity<PessoaJuridicaCadastroDTO> cadastrarPessoaJuridica(
            @Valid @RequestBody PessoaJuridicaCadastroDTO pessoaJuridica) throws URISyntaxException {
        try {
            pessoaJuridicaService.verificaExistenciaNovaPJ(pessoaJuridica.getId());
            PessoaJuridicaCadastroDTO result;
            result = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);
            return ResponseEntity.created(new URI("/api/pessoa-juridica/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                    .body(result);
        } catch (CreateEntityWithExistentIdException | CNPJInvalidoException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(
                    ENTITY_NAME, e.getMessage())).body(null);
        }   
    }

    @PutMapping("/pessoa-juridica")
    @Timed
    public ResponseEntity<PessoaJuridicaCadastroDTO> atualizarPessoaJuridica(
            @Valid @RequestBody PessoaJuridicaCadastroDTO pessoaJuridica) throws URISyntaxException {
        if (pessoaJuridica.getId() == null) {
            return cadastrarPessoaJuridica(pessoaJuridica);
        }
        try {
            PessoaJuridicaCadastroDTO result = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId()
                            .toString())).body(result);
        } catch (CreateEntityWithExistentIdException | CNPJInvalidoException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(
                    ENTITY_NAME, e.getMessage())).body(null);
        }
    }

    @DeleteMapping("/pessoa-juridica/{id}")
    @Timed
    public ResponseEntity<PessoaJuridicaListaDTO> removerPessoaJuridica(@PathVariable("id") Long id) {
        try {
            pessoaJuridicaService.excluirPessoaJuridica(id);
        } catch (DeleteInexistentEntityException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, e.getMessage()))
                    .body(null);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pessoa-juridica/nomes")
    @Timed
    public ResponseEntity<List<EnumerationDTO>> getAllNomePessoasJuridicas() {
        List<PessoaJuridicaNomeDTO> nomesPessoaJuridica = pessoaJuridicaService.getNomesByPJ();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(enumerationService
                .getAllNomesPJ(nomesPessoaJuridica)));
    }

    @PostMapping("/pessoa-juridica/alterar-senha")
    @Timed
    public ResponseEntity<?> alterarSenha(@Valid @RequestBody CredenciaisNovaSenhaDTO credenciais)
            throws URISyntaxException {
        try {
            pessoaJuridicaService.alterarSenha(credenciais);
            return ResponseEntity.ok(null);
        } catch (NullCPNJException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, e.getMessage()))
                    .body(null);
        }
    }

}
