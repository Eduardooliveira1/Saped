package br.gov.mme.web.rest;

import br.gov.mme.exceptions.EntityNotExistException;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.RepresentanteService;
import br.gov.mme.service.dto.PessoaRepresentanteListaDTO;
import br.gov.mme.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
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

    public static final String ENTITY_NAME = "pessoas-representantes";

    private final Logger log = LoggerFactory.getLogger(PessoaRepresentanteResource.class);

    public PessoaRepresentanteResource(RepresentanteService representanteService) {
        this.representanteService = representanteService;
    }

    @GetMapping("/pessoas-representantes/{idPj}")
    @Timed
    public ResponseEntity<List<PessoaRepresentanteListaDTO>> obterPessoaRepesentantes(@PathVariable("idPj") Long idpj) throws URISyntaxException {
        List<PessoaRepresentanteListaDTO> result = null;
        try {
            result = representanteService.findRepresentantesByPessoaJuridica(idpj);
        } catch (EntityNotExistException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(
                    ENTITY_NAME, e.getMessage())).body(null);
        }
        return ResponseEntity.ok(result);
    }

}
