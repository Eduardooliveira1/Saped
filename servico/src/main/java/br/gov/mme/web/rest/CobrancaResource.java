package br.gov.mme.web.rest;

import br.gov.mme.exceptions.FiltroVazioException;
import br.gov.mme.service.CobrancaService;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoDTO;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.dto.CobrancaDTO;
import br.gov.mme.service.dto.FiltroListagemCobrancaDTO;
import br.gov.mme.web.rest.util.HeaderUtil;
import br.gov.mme.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CobrancaResource {

    private final CobrancaService cobrancaService;

    private final Logger log = LoggerFactory.getLogger(RelatorioResource.class);

    public CobrancaResource(CobrancaService cobrancaService) {
        this.cobrancaService  = cobrancaService;
    }

    @PostMapping("/listagem-cobranca")
    @Timed
    public ResponseEntity<List<CobrancaDTO>> obterListagemAnualCobranca(@Valid @RequestBody
                                                      FiltroListagemCobrancaDTO filtro) throws URISyntaxException {

            List<CobrancaDTO> listagemCobranca = this.cobrancaService.obterCobrancasDoAno(filtro);
            return new ResponseEntity<>(listagemCobranca, HttpStatus.OK);
    }
}
