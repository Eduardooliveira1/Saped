package br.gov.mme.web.rest;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.gov.mme.SapedApp;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.utils.ConstUtilsForTests;
import br.gov.mme.utils.FuncUtilsForTests;
import br.gov.mme.utils.ObjectsUtilsForTests;


/**
 * Test class for the PessoaJuridica REST controller.
 *
 * @see PessoaJuridicaResource
 **/
@SpringBootTest(classes = SapedApp.class)
public class PessoaJuridicaResourceIntTest {

	@ClassRule
	public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

	@Rule
	public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private EntityManager em;

    PessoaJuridica pessoaJuridica;

	private MockMvc restPessoaJuridicaMockMvc;

	private static final String GET_PJS = "/api/pessoas-juridicas";

    public static PessoaJuridica createEntity(EntityManager em){
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setPessoa(ObjectsUtilsForTests.getDefaultPessoa());
		pessoaJuridica.setCnpj(ConstUtilsForTests.DEFAULT_STRING_TAM_14);
		pessoaJuridica.setNomeFantasia(ConstUtilsForTests.DEFAULT_STRING_TAM_9);
		pessoaJuridica.setRazaoSocial(ConstUtilsForTests.DEFAULT_STRING_TAM_9);
		pessoaJuridica.setSigla(ConstUtilsForTests.DEFAULT_STRING_TAM_9);
        return pessoaJuridica;
    }

    @Before
    public void setup(){
		MockitoAnnotations.initMocks(this);

		LogsResource logsResource = new LogsResource();
		this.restPessoaJuridicaMockMvc = MockMvcBuilders.standaloneSetup(logsResource).build();

		this.pessoaJuridicaRepository.deleteAll();
		pessoaJuridica = createEntity(this.em);
    }

    @Test
    public void listarPessoasJuridicasTest() throws Exception{
        this.pessoaJuridicaRepository.saveAndFlush(pessoaJuridica);
		FuncUtilsForTests.performGet(restPessoaJuridicaMockMvc, GET_PJS);
    }

}
