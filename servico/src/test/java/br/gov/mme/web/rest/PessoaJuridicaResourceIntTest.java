package br.gov.mme.web.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.SapedApp;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.utils.ConstUtilsForTests;
import br.gov.mme.utils.FuncUtilsForTests;
import br.gov.mme.utils.ObjectsUtilsForTests;
import br.gov.mme.web.rest.errors.ExceptionTranslator;


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
	private PessoaJuridicaService pessoaJuridicaService;

    @Autowired
    private EntityManager em;

	@Autowired
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	@Autowired
	private ExceptionTranslator exceptionTranslator;

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

		PessoaJuridicaResource pessoaJuridicaResource = new PessoaJuridicaResource(pessoaJuridicaService);

		this.restPessoaJuridicaMockMvc = MockMvcBuilders.standaloneSetup(pessoaJuridicaResource)
				.setCustomArgumentResolvers(this.pageableArgumentResolver).setControllerAdvice(this.exceptionTranslator)
				.setMessageConverters(this.jacksonMessageConverter).build();
		this.em.clear();
		this.pessoaJuridicaRepository.deleteAllInBatch();
		pessoaJuridica = createEntity(this.em);
    }

    @Test
	@Transactional
    public void listarPessoasJuridicasTest() throws Exception{
        this.pessoaJuridicaRepository.saveAndFlush(pessoaJuridica);
		FuncUtilsForTests.performGet(restPessoaJuridicaMockMvc, GET_PJS).andDo(print());
    }

}
