package br.gov.mme.web.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.DateUtilService;
import br.gov.mme.service.dto.EnumerationDTO;

@RestController
@RequestMapping("/api")
public class DateUtilResource {

    public DateUtilResource(DateUtilService dateUtilService) {
        this.dateUtilService = dateUtilService;
    }

    private final DateUtilService dateUtilService;

    @GetMapping("/date-util/mes-referencia")
    @Timed
    public ResponseEntity<List<EnumerationDTO>> listarMesReferencia() {
        return new ResponseEntity<>(dateUtilService.listarMesReferencia(), HttpStatus.OK);
    }

}
