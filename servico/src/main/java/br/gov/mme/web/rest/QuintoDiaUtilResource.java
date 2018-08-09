package br.gov.mme.web.rest;

import br.gov.mme.service.QuintoDiaUtilService;

import com.codahale.metrics.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class QuintoDiaUtilResource {

    private final QuintoDiaUtilService quintoDiaUtilService;

    public QuintoDiaUtilResource(QuintoDiaUtilService quintoDiaUtilService) {
        this.quintoDiaUtilService = quintoDiaUtilService;
    }

    @GetMapping("/quinto-dia-util/{id}")
    @Timed
    public ResponseEntity<List<String>> obterQuintosDiasUteis(@PathVariable("id") int id) {
        List<String> result =  quintoDiaUtilService.obterQuintosDiasUteis(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
