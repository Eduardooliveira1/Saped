package br.gov.mme.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.PessoaJuridicaListaDTO;
import br.gov.mme.web.rest.util.PaginationUtil;

/**
 * REST controller for managing PessoaJuridicaResource.
 * 
 * @see PessoaJuridicaService
 */
@RestController
@RequestMapping("/api/")
public class PessoaJuridicaResource {

    private final PessoaJuridicaService pessoaJuridicaService;

    public PessoaJuridicaResource(PessoaJuridicaService pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @GetMapping("/pessoas-juridicas")
    @Timed
    public ResponseEntity<Page<PessoaJuridicaListaDTO>> listarPessoasJuridicas(@RequestParam(value = "query", required = false) String query, Pageable pageable) {
        Page<PessoaJuridicaListaDTO> page = pessoaJuridicaService.listarPessoasJuridicas(query,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pessoas-juridicas");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
}
