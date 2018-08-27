package br.gov.mme.web.rest;

import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.RepresentanteService;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;
import com.codahale.metrics.annotation.Timed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<PessoaRepresentanteListaDTO>> obterPessoaRepesentantes(@PathVariable("idPj") Long idpj)  {
        List<PessoaRepresentanteListaDTO> listaRepresentantes = representanteService.findRepresentantesByPessoaJuridica(idpj);
        return ResponseEntity.ok(listaRepresentantes);
    }

}
