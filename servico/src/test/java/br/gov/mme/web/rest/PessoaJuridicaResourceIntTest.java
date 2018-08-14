package br.gov.mme.web.rest;

import br.gov.mme.SapedApp;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.domain.Representante;
import br.gov.mme.domain.Telefone;
import br.gov.mme.enumeration.EntityFields;
import br.gov.mme.enumeration.FlNotificacao;
import br.gov.mme.enumeration.FlStatus;
import br.gov.mme.exceptions.ExceptionMessages;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.repository.PessoaRepository;
import br.gov.mme.service.PessoaJuridicaService;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.service.mapper.PessoaJuridicaMapper;
import br.gov.mme.utils.TestUtils;
import br.gov.mme.web.rest.errors.ExceptionTranslator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Test class for the PessoaJuridica REST controller.
 *
 * @see PessoaJuridicaResource
 **/
@SpringBootTest(classes = SapedApp.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@TestInstance(Lifecycle.PER_CLASS)
public class PessoaJuridicaResourceIntTest {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

	@Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @Autowired
    private PessoaJuridicaMapper pessoaJuridicaMapper;

	@Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

	@Autowired
    private ExceptionTranslator exceptionTranslator;

	private PessoaJuridica pessoaJuridica;

	private Representante representante;

    private static MockMvc restPessoaJuridicaMockMvc;
	
    private static final String API = "/api/pessoa-juridica";

    private static final String REST_WITH_URL_PARAM = API + "/{id}";

    private static final String GET_PJS = "/api/pessoas-juridicas";

    private static final String GET_PJ = REST_WITH_URL_PARAM;

    private static final String DEL_PJ = REST_WITH_URL_PARAM;
    
    private static final String POST_PJ = API;

    private static final String UPDATE_PJ = API;

    private static final String GET_TODAS_PJS = GET_PJS + "/todas";

    private static final String GET_REPRESENTANTES = API +"/representantes/{idPj}";

    private static final String ENTITY_NAME = PessoaJuridicaResource.ENTITY_NAME;

    public static PessoaJuridica createEntity() {
        return createEntityBase(TestUtils.DEFAULT_STRING_TAM_9, TestUtils.DEFAULT_VALID_CNPJ);
	}

    public static Representante createEntityRepresentante() {
        return createEntityRepresentanteBase();
    }

    public static PessoaJuridica createDiferentEntity() {
        return createEntityBase(TestUtils.UPDATED_STRING_TAM_9, TestUtils.UPDATED_VALID_CNPJ);
	}

    private static PessoaJuridica createEntityBase(String nome, String cpf) {
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setPessoa(TestUtils.getDefaultPessoa());
		pessoaJuridica.setCnpj(cpf);
		pessoaJuridica.setNomeFantasia(nome);
		pessoaJuridica.setRazaoSocial(nome);
		pessoaJuridica.setSigla(nome);
		return pessoaJuridica;
	}

    private static Representante createEntityRepresentanteBase() {
        Representante representante =  new Representante();
        representante.setPessoa(TestUtils.getDefaultPessoa());
        representante.setNome(TestUtils.DEFAULT_STRING_TAM_9);
        representante.setCargo(TestUtils.DEFAULT_STRING_TAM_9);
        List<Telefone> representanteTelefones = new ArrayList<>();
        representanteTelefones.add(createDefaultTelenone(representante));
        representanteTelefones.add(createDefaultTelenone(representante));
        representante.setTelefone(representanteTelefones);
        representante.setNotificacao(FlNotificacao.S);


        representante.getPessoa().setEmail(TestUtils.DEFAULT_EMAIL);

        return representante;
    }

    private static Telefone createDefaultTelenone( Representante representante) {
        Telefone telefone = new Telefone();
        telefone.setStatus(FlStatus.S);
        telefone.setPessoaRepresentante(representante);
        telefone.setDdd(TestUtils.DEFAULT_BIGDECIMAL_DDD);
        telefone.setTelefone(TestUtils.DEFAUL_BIGDECIMAL_TELEFONE);

        return telefone;
    }


    private ResultActions checkarDadosPJ(ResultActions resultActions, String array, PessoaJuridica pessoaJuridica)
            throws Exception {
        resultActions = resultActions.andExpect(jsonPath(array + "cnpj").value(pessoaJuridica.getCnpj()))
                .andExpect(jsonPath(array + "sigla").value(pessoaJuridica.getSigla()))
                .andExpect(jsonPath(array + "nomeFantasia").value(pessoaJuridica.getNomeFantasia()))
                .andExpect(jsonPath(array + "razaoSocial").value(pessoaJuridica.getRazaoSocial()));
        return resultActions;
    }

	private void multipleSaveAndFlush(PessoaJuridica... pessoasJuridicas) {
		for (PessoaJuridica pessoaJuridica : pessoasJuridicas) {
			this.pessoaJuridicaRepository.saveAndFlush(pessoaJuridica);
		}
	}

    @BeforeEach
    public void setup() {
        PessoaJuridicaResource pessoaJuridicaResource = new PessoaJuridicaResource(pessoaJuridicaService);

        restPessoaJuridicaMockMvc = TestUtils.setupMockMvc(pessoaJuridicaResource, pageableArgumentResolver,
                jacksonMessageConverter, exceptionTranslator);
		this.pessoaJuridicaRepository.deleteAll();
		this.pessoaJuridicaRepository.flush();
        pessoaJuridica = createEntity();
        representante = createEntityRepresentante();
	}

    @SuppressWarnings("unused")
    private static Stream<Arguments> argsPJWithExceptions() {
        Object[][] params = new Object[][] {
                { TestUtils.getPJCadastroWithId(),
                        ExceptionMessages.CREATE_EXISTENT_ID.message(ENTITY_NAME), },
                { TestUtils.getPJCadastroWithInvalidCNPJ(), ExceptionMessages.CREATE_INVALID_FIELD
                        .message(ENTITY_NAME, EntityFields.CNPJ.field()) }
        };
        return Stream.of(Arguments.of(params[0]), Arguments.of(params[1]));
    }

    @Test
    @Transactional
    public void listarPessoasJuridicas() throws Exception {
        PessoaJuridica pessoaJuridicaFlStatusN = createDiferentEntity();
        pessoaJuridicaFlStatusN.getPessoa().setStatus(FlStatus.N);
        this.multipleSaveAndFlush(this.pessoaJuridica, pessoaJuridicaFlStatusN);
        checkarDadosPJ(TestUtils.performGet(restPessoaJuridicaMockMvc, GET_PJS), "$.content[0].", 
                this.pessoaJuridica).andExpect(jsonPath("$.totalElements").value(1))
        .andExpect(jsonPath("$.numberOfElements").value(1))
        .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void listarPessoasJuridicasPorNomeFantasia() throws Exception {
        PessoaJuridica pessoaJuridicaRetornada = createDiferentEntity();
        this.multipleSaveAndFlush(this.pessoaJuridica, pessoaJuridicaRetornada);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", pessoaJuridicaRetornada.getCnpj());

        TestUtils.performGetWithParams(restPessoaJuridicaMockMvc, GET_PJS, params).andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].cnpj").value(pessoaJuridicaRetornada.getCnpj()))
                .andExpect(jsonPath("$.content[0].nomeFantasia")
                        .value(pessoaJuridicaRetornada.getNomeFantasia()))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.numberOfElements").value(1));
    }

    @Test
    @Transactional
    public void obterPessoaJuridica() throws Exception {
        this.pessoaJuridicaRepository.saveAndFlush(this.pessoaJuridica);
        checkarDadosPJ(TestUtils.performGet(restPessoaJuridicaMockMvc, GET_PJ, 
                this.pessoaJuridica.getId()), "$.", this.pessoaJuridica).andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void obterPessoaJuridicaNaoExistente() throws Exception {
        assertEquals(TestUtils.performGet(restPessoaJuridicaMockMvc, GET_PJ, TestUtils.DEFAULT_VALID_ID)
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString(), "");
    }

    @Test
    @Transactional
    public void exlcuirPessoaJuridica() throws Exception {
        this.pessoaJuridicaRepository.saveAndFlush(this.pessoaJuridica);
        TestUtils.performDelete(restPessoaJuridicaMockMvc, DEL_PJ, this.pessoaJuridica.getId())
                .andExpect(status().isOk());
        assertEquals(this.pessoaJuridica.getPessoa().getStatus(), FlStatus.N);
    }

    @Test
    @Transactional
    public void exlcuirPessoaJuridicaInexistente() throws Exception {
        TestUtils.performDeleteWithException(restPessoaJuridicaMockMvc, DEL_PJ,
                ExceptionMessages.DELETE_INEXISTENT_ID.message(ENTITY_NAME), ENTITY_NAME,
                TestUtils.DEFAULT_VALID_ID);
    }

    @Test
    @Transactional
    public void adicionarPessoaJuridica() throws Exception {
        PessoaJuridicaCadastroDTO pessoaJuridicaCadastroDTO = pessoaJuridicaMapper.toDto(this.pessoaJuridica);
        TestUtils.performPost(restPessoaJuridicaMockMvc, POST_PJ, pessoaJuridicaCadastroDTO);
    }

    @ParameterizedTest
    @Transactional
    @MethodSource("argsPJWithExceptions")
    public void adicionarPessoaJuridicaWithExceptions(PessoaJuridicaCadastroDTO pessoaJuridicaCadastroDTO, 
            String error) throws Exception {
        pessoaJuridicaRepository.saveAndFlush(this.pessoaJuridica);
        TestUtils.performPostWithExceptions(restPessoaJuridicaMockMvc, POST_PJ, pessoaJuridicaCadastroDTO, 
                error, ENTITY_NAME);
    }

    @Test
    @Transactional
    public void atualizarPessoaJuridicaCriandoNova() throws Exception {
        PessoaJuridicaCadastroDTO pessoaJuridicaCadastroDTO = pessoaJuridicaMapper.toDto(this.pessoaJuridica);
        checkarDadosPJ(TestUtils.performPut(restPessoaJuridicaMockMvc, UPDATE_PJ, 
                pessoaJuridicaCadastroDTO), "$.",
                this.pessoaJuridica).andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void atualizarPessoaJuridicaExistente() throws Exception {
        this.pessoaJuridicaRepository.saveAndFlush(this.pessoaJuridica);
        Long id = this.pessoaJuridica.getId();
        PessoaJuridicaCadastroDTO pessoaJuridicaCadastroDTO = pessoaJuridicaMapper.toDto(this.pessoaJuridica);
        pessoaJuridicaCadastroDTO.setRazaoSocial(TestUtils.UPDATED_STRING_TAM_9);
        TestUtils.performPut(restPessoaJuridicaMockMvc, UPDATE_PJ, pessoaJuridicaCadastroDTO)
        .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id)).andExpect(jsonPath("$.cnpj")
                        .value(TestUtils.DEFAULT_VALID_CNPJ))
                .andExpect(jsonPath("$.sigla").value(TestUtils.DEFAULT_STRING_TAM_9))
                .andExpect(jsonPath("$.nomeFantasia").value(TestUtils.DEFAULT_STRING_TAM_9))
                .andExpect(jsonPath("$.razaoSocial").value(TestUtils.UPDATED_STRING_TAM_9));

        assertEquals(this.pessoaJuridicaRepository.count(), 1);
    }

    @ParameterizedTest
    @Transactional
    @MethodSource("argsPJWithExceptions")
    public void atualizarPessoaJuridicaWithException(PessoaJuridicaCadastroDTO pessoaJuridicaCadastroDTO, 
            String error) throws Exception {
        this.pessoaJuridicaRepository.saveAndFlush(this.pessoaJuridica);
        TestUtils.performPutWithExceptions(restPessoaJuridicaMockMvc, UPDATE_PJ, pessoaJuridicaCadastroDTO,
                error, ENTITY_NAME);
    }

    @Test
    @Transactional
    public void listarTodas() throws Exception {
        PessoaJuridica outraPessoaJuridica = createDiferentEntity();
        this.multipleSaveAndFlush(this.pessoaJuridica, outraPessoaJuridica);
        TestUtils.performGet(this.restPessoaJuridicaMockMvc,GET_TODAS_PJS)
                .andExpect(jsonPath("$[0].id").value(this.pessoaJuridica.getId()))
                .andExpect(jsonPath("$[0].nome").value(this.pessoaJuridica.getNomeFantasia()))
                .andExpect(jsonPath("$[1].id").value(outraPessoaJuridica.getId()))
                .andExpect(jsonPath("$[1].nome").value(outraPessoaJuridica.getNomeFantasia()))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Transactional
    public void listarTodosRepresentantesPorIdPj() throws Exception {

        this.pessoaJuridica.setRepresentantes( new ArrayList<>(Arrays.asList(this.representante)));
        this.representante.setPessoaJuridica(pessoaJuridica);

        this.pessoaJuridicaRepository.saveAndFlush(this.pessoaJuridica);

        TestUtils.performGet(this.restPessoaJuridicaMockMvc, GET_REPRESENTANTES, pessoaJuridica.getId())
                .andExpect(jsonPath("$.[0].nome").value(this.representante.getNome()))
                .andExpect(jsonPath("$.[0].cargo").value(this.representante.getCargo()))
                .andExpect(jsonPath("$.[0].email").value(this.representante.getPessoa().getEmail()))
                .andExpect(jsonPath("$.[0].telefone[0].ddd").value(this.representante.getTelefone().get(0).getDdd()))
                .andExpect(jsonPath("$.[0].telefone[0].telefone").value(this.representante.getTelefone().get(0).getTelefone()));
    }
}
