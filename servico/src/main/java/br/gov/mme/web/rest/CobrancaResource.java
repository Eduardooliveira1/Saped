package br.gov.mme.web.rest;

import br.gov.mme.service.CobrancaService;
import br.gov.mme.service.dto.cobrancaDTO;
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
public class CobrancaResource {

    private final CobrancaService cobrancaService;

    public CobrancaResource(CobrancaService cobrancaService) {
        this.cobrancaService  = cobrancaService;
    }

    @GetMapping("/listagem-cobranca/{ano}/{idPj}")
    @Timed
    public ResponseEntity<List<cobrancaDTO>> obterListagemAnualCobranca(@PathVariable("ano") int ano, @PathVariable("idPj") int idPj) {
        List<cobrancaDTO> result =  cobrancaService.obterCobrancasDoAno(ano, idPj);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
