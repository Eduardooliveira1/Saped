package br.gov.mme.web.rest;

import br.gov.mme.service.EnumerationService;
import br.gov.mme.service.dto.EnumerationDTO;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EnumerationResource {

    private final EnumerationService enumerationService;

    public EnumerationResource(EnumerationService enumerationService) {
        this.enumerationService = enumerationService;
    }

    @GetMapping("/enumerations/tipos-endereco")
    @Timed
    public ResponseEntity<List<EnumerationDTO>> getAllSituacaoConcessao() {
        List<EnumerationDTO> enumerationDTOList = enumerationService.getAllTipoEndereco();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(enumerationDTOList));
    }

}
