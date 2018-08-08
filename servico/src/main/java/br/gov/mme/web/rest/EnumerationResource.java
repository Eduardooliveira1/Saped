package br.gov.mme.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.EnumerationService;
import br.gov.mme.service.dto.EnumerationDTO;
import io.github.jhipster.web.util.ResponseUtil;

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

    @GetMapping("/enumerations/nomes-pessoas-juridicas")
    @Timed
    public ResponseEntity<List<EnumerationDTO>> getAllNomePessoasJuridicas() {
        List<EnumerationDTO> enumerationDTOList = enumerationService.getAllNomesPjs();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(enumerationDTOList));
    }
    
    @GetMapping("/enumerations/status-boleto")
    @Timed
    public ResponseEntity<List<EnumerationDTO>> getAllStatusBoleto() {
        List<EnumerationDTO> enumerationDTOList = enumerationService.getAllStatusBoleto();
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(enumerationDTOList));
    }

}
