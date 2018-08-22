package br.gov.mme.service.impl;

import br.gov.mme.service.CobrancaService;
import br.gov.mme.service.dto.cobrancaDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CobrancaServiceImpl implements CobrancaService {

    @Override
    public List<cobrancaDTO> obterCobrancasDoAno(int ano, int idPj) {
        /* TODO::verificar se há boletos para o ano em questão
            caso tenha, pega todos os doze meses e retorna para o usuário
            caso não tenha, gerar os 12 meses, persistir no banco de dados e apresentar para o usuário
        */

        // obtém todos os boletos relacionados à uma pessoa jurídica (idPj) no determinado ano (list boleto)

        // se a lista de boletos estiver vazia, crie 


        List<cobrancaDTO> cobrancasDoAno = new ArrayList<>();
        return  cobrancasDoAno;
    }

}
