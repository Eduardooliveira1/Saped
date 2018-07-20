package br.gov.mme.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * Funções auxiliares compartilhadas para os testes.
 **/
public abstract class FuncUtilsForTests {

	private FuncUtilsForTests() {
	}
	
	 public static ResultActions performGet(MockMvc mock, String path, Object... vars) throws Exception {
	        return mock.perform(get(path, vars).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	            .content(new byte[0]));
	    }

}
