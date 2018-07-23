package br.gov.mme.web.rest;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

	public static PessoaJuridica createEntity(EntityManager em) {
		return createEntityBase(em, ConstUtilsForTests.DEFAULT_STRING_TAM_9);

	}

	public static PessoaJuridica createDiferentEntity(EntityManager em) {
		return createEntityBase(em, ConstUtilsForTests.UPDATED_STRING_TAM_9);
	}

	private static PessoaJuridica createEntityBase(EntityManager em, String nome) {
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setPessoa(ObjectsUtilsForTests.getDefaultPessoa());
		pessoaJuridica.setCnpj(String.valueOf(FuncUtilsForTests.getNextId()));
		pessoaJuridica.setNomeFantasia(nome);
		pessoaJuridica.setRazaoSocial(nome);
		pessoaJuridica.setSigla(nome);
		return pessoaJuridica;
	}

	private void multipleSaveAndFlush(PessoaJuridica... pessoasJuridicas) {
		for (PessoaJuridica pessoaJuridica : pessoasJuridicas) {
			this.pessoaJuridicaRepository.saveAndFlush(pessoaJuridica);
		}
	}

	@Before
	public void setup() {
		initMocks(this);

		PessoaJuridicaResource pessoaJuridicaResource = new PessoaJuridicaResource(pessoaJuridicaService);

		restPessoaJuridicaMockMvc = FuncUtilsForTests.setupMockMvc(pessoaJuridicaResource, pageableArgumentResolver,
				jacksonMessageConverter, exceptionTranslator);

		this.pessoaJuridicaRepository.deleteAll();
		this.pessoaJuridicaRepository.flush();
		pessoaJuridica = createEntity(this.em);
	}

	@Test
	@Transactional
	public void listarPessoasJuridicasTest() throws Exception {
		PessoaJuridica pessoaJuridicaFlStatusN = createEntity(this.em);
		pessoaJuridicaFlStatusN.getPessoa().setStatus(ConstUtilsForTests.UPDATED_FL_STATUS);
		this.multipleSaveAndFlush(this.pessoaJuridica, pessoaJuridicaFlStatusN);
		FuncUtilsForTests.performGet(restPessoaJuridicaMockMvc, GET_PJS)
				.andExpect(jsonPath("$.content[0].cnpj").value(this.pessoaJuridica.getCnpj()))
				.andExpect(jsonPath("$.content[0].sigla").value(this.pessoaJuridica.getSigla()))
				.andExpect(jsonPath("$.content[0].nomeFantasia").value(this.pessoaJuridica.getNomeFantasia()))
				.andExpect(jsonPath("$.content[0].razaoSocial").value(this.pessoaJuridica.getRazaoSocial()))
				.andExpect(jsonPath("$.totalElements").value(1)).andExpect(jsonPath("$.numberOfElements").value(1));
	}

	@Test
	@Transactional
	public void listarPessoasJuridicasPorNomeFantasia() throws Exception {
		PessoaJuridica pessoaJuridicaRetornada = createDiferentEntity(this.em);
		this.multipleSaveAndFlush(this.pessoaJuridica, pessoaJuridicaRetornada);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("query", pessoaJuridicaRetornada.getCnpj());

		FuncUtilsForTests
				.performGetWithParams(restPessoaJuridicaMockMvc, GET_PJS, params)
				.andDo(print())
				.andExpect(jsonPath("$.content[0].cnpj").value(pessoaJuridicaRetornada.getCnpj()))
				.andExpect(jsonPath("$.content[0].nomeFantasia").value(pessoaJuridicaRetornada
						.getNomeFantasia()))
				.andExpect(jsonPath("$.totalElements").value(1))
				.andExpect(jsonPath("$.numberOfElements").value(1));
    }

}
