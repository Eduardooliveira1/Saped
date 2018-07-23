package br.gov.mme.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;

import br.gov.mme.domain.Pessoa;
import br.gov.mme.enumerator.FlStatus;
import br.gov.mme.web.rest.errors.ExceptionTranslator;

public abstract class TestUtils {

	private TestUtils() {
	}

	// CONSTANTES:

	public static final String DEFAULT_STRING_TAM_9 = "aaaaaaaaa";

	public static final String UPDATED_STRING_TAM_9 = "bbbbbbbbb";

	public static final String DEFAULT_VALID_CNPJ = "11111111111111";

	public static final String UPDATED_VALID_CNPJ = "22222222222222";

	public static final LocalDateTime DATE_TIME_NOW = LocalDateTime.now();

	// FUNÇÕES AUXILIARES:
	
	private static MockHttpServletRequestBuilder RESTGetComum(MockMvc mock,
			String path, Object... vars) {
		return get(path, vars).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(new byte[0]);
	}

	public static ResultActions performGet(MockMvc mock, String path, Object... vars) throws Exception {
		return mock.perform(RESTGetComum(mock, path, vars));
	}

	public static ResultActions performGetWithParams(MockMvc mock, String path, 
			MultiValueMap<String, String> params, Object... vars) throws Exception {
		return mock.perform(RESTGetComum(mock, path, vars).params(params));
	}

	public static MockMvc setupMockMvc(Object resource,
			PageableHandlerMethodArgumentResolver pageableArgumentResolver,
			MappingJackson2HttpMessageConverter jacksonMessageConverter, ExceptionTranslator exceptionTranslator) {
		return MockMvcBuilders.standaloneSetup(resource)
			.setCustomArgumentResolvers(pageableArgumentResolver)
			.setControllerAdvice(exceptionTranslator)
			.setMessageConverters(jacksonMessageConverter).build();
	}
	
	//GERADORES DE OBJETOS:
	
	public static Pessoa getDefaultPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setDataCadastro(DATE_TIME_NOW);
		pessoa.setStatus(FlStatus.S);
		return pessoa;
	}

}
