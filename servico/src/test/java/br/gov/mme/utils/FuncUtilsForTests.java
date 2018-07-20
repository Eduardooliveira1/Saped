package br.gov.mme.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.gov.mme.web.rest.errors.ExceptionTranslator;

/**
 * Funções auxiliares compartilhadas para os testes.
 **/
public abstract class FuncUtilsForTests {

	private FuncUtilsForTests() {
	}
	
	private static int nextId = 0;

	public static ResultActions performGet(MockMvc mock, String path, Object... vars) throws Exception {
	        return mock.perform(get(path, vars).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	            .content(new byte[0]));
	}

	public static MockMvc setupMockMvc(Object resource,
			PageableHandlerMethodArgumentResolver pageableArgumentResolver,
			MappingJackson2HttpMessageConverter jacksonMessageConverter, ExceptionTranslator exceptionTranslator) {
		return MockMvcBuilders.standaloneSetup(resource)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(exceptionTranslator)
				.setMessageConverters(jacksonMessageConverter).build();
	}

	public static int getNextId() {
		return nextId++;
	}

}
