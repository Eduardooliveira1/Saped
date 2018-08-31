package br.gov.mme.web.rest;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.RepresentanteService;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;
import br.gov.mme.service.dto.RepresentanteEMailECNPJDTO;

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

    @PostMapping("/pessoas-representantes/validar-esqueci-a-senha")
    @Timed
    public ResponseEntity<Boolean> validarEsqueciASenha(@Valid @RequestBody RepresentanteEMailECNPJDTO representante)
            throws URISyntaxException {
        Boolean validResponse = representanteService.verificaCNPJEEmailValidos(representante);
        return ResponseEntity.ok(validResponse);
    }

}
