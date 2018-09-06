package br.gov.mme.web.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.RepresentanteService;
import br.gov.mme.service.dto.ComunicacaoRepresentantelistaDTO;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;
import br.gov.mme.web.rest.util.PaginationUtil;

/**
 * REST controller for managing PessoaJuridicaResource.
 *
 * @see PessoaJuridicaService
 */
@RestController
@RequestMapping("/api/")
public class PessoaRepresentanteResource {

    private final RepresentanteService representanteService;

    public PessoaRepresentanteResource(RepresentanteService representanteService) {
        this.representanteService = representanteService;
    }

    @GetMapping("/pessoas-representantes/{idPj}")
    @Timed
    public ResponseEntity<List<PessoaRepresentanteListaDTO>> obterPessoaRepesentantes(@PathVariable("idPj") Long idpj) {
        List<PessoaRepresentanteListaDTO> listaRepresentantes = representanteService
            .findRepresentantesByPessoaJuridica(idpj);
        return ResponseEntity.ok(listaRepresentantes);
    }

    @GetMapping("/representantes")
    @Timed
    public ResponseEntity<Page<ComunicacaoRepresentantelistaDTO>> listarPessoasJuridicas(
        @RequestParam(value = "query", required = false) String query, Pageable pageable) {
        Page<ComunicacaoRepresentantelistaDTO> page = representanteService.listarRepresentantes(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/representantes");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

}
