package br.gov.mme.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.gov.mme.SapedApp;
import br.gov.mme.domain.Pessoa;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.repository.PessoaJuridicaRepository;


/**
 * Test class for the ProfileInfoResource REST controller.
 *
 * @see ProfileInfoResource
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SapedApp.class)
public class PessoaJuridicaResourceIntTest {

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private EntityManager em;

    PessoaJuridica pessoaJuridica;

    private MockMvc restProfileMockMvc;


    public static PessoaJuridica createEntity(EntityManager em){
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        Pessoa pessoa = new Pessoa();
        pessoa.setDataCadastro(LocalDateTime.now());
        em.persist(pessoa);
        em.flush();
        pessoaJuridica.setPessoa(pessoa);
        pessoaJuridica.setCnpj("11111111111111");
        pessoaJuridica.setNomeFantasia("DEFAULT_NOME");
        pessoaJuridica.setRazaoSocial("DEFAULT_RAZAO_SOCIAL");
        pessoaJuridica.setSigla("DEFAULT_SIGLA");
        return pessoaJuridica;
    }

    @Before
    public void setup(){
		pessoaJuridica = createEntity(this.em);
    }

    @Test
    public void listarPessoasJuridicasTest() throws Exception{
        this.pessoaJuridicaRepository.saveAndFlush(pessoaJuridica);
		restProfileMockMvc.perform(get("/api/pessoas-juridicas/"));

    }


}
