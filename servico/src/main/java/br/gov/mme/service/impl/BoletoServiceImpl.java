package br.gov.mme.service.impl;

import br.gov.mme.service.BoletoService;
import br.gov.mme.service.dto.CobrancaDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing PessoaJuridica.
 */

@Service
@Transactional
public class BoletoServiceImpl implements BoletoService {

    public static final String ENTITY_NAME = "pessoa-juridica";

    public BoletoServiceImpl() { }

    @Override
    public CobrancaDTO gerarBoleto (CobrancaDTO cobranca) {
        //TODO::Gerar o boleto
        // Persistir no banco de dados
        // Chamar o método de criar cobrança que está no CobrançaServiceImpl passando o um CobrancaBoletoDTO como parametro
        // Retornar a conbrançaDTO criada pelo método
        return cobranca;
    }


}
