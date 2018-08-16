package br.gov.mme.web.rest;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.gov.mme.service.dto.UsuarioDTO;

@RestController
@RequestMapping("/api/login")
public class LoginResource {


    @PostMapping
    @Timed
    public ResponseEntity efetuarLogin(@Valid @RequestBody UsuarioDTO usuario) throws URISyntaxException {

       return null;
    }


}
