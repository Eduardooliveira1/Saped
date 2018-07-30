package br.gov.mme.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
import br.gov.mme.enumeration.FlStatus;
import br.gov.mme.service.dto.PessoaJuridicaCadastroDTO;
import br.gov.mme.web.rest.TestUtil;
import br.gov.mme.web.rest.errors.ExceptionTranslator;

public final class TestUtils {

	private TestUtils() {
	}

    // ATRIBUTOS:


	// CONSTANTES:

	public static final String DEFAULT_STRING_TAM_9 = "aaaaaaaaa";

	public static final String UPDATED_STRING_TAM_9 = "bbbbbbbbb";

	public static final String DEFAULT_VALID_CNPJ = "87772272000198";

	public static final String UPDATED_VALID_CNPJ = "83645050000153";

    public static final String INVALID_CNPJ = "11111111111111";

    public static final Long DEFAULT_INVALID_ID = 1L;

	public static final LocalDateTime DATE_TIME_NOW = LocalDateTime.now();

    public static final String EXCPT_URL_TYPE = "http://www.jhipster.tech/problem/problem-with-message";

	// FUNÇÕES AUXILIARES:
	
    private static MockHttpServletRequestBuilder RESTGetComum(
			String path, Object... vars) {
		return get(path, vars).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(new byte[0]);
	}

    public static ResultActions performGet(MockMvc mock, String path, Object... vars) throws GenericException {
        ResultActions result = null;
        try {
            result = mock.perform(RESTGetComum(path, vars));
        } catch (Exception e) {
            throw ExceptionUtils.convertToGeneric(e);
        }
        return result;
	}

	public static ResultActions performGetWithParams(MockMvc mock, String path, 
            MultiValueMap<String, String> params, Object... vars) throws GenericException {
        ResultActions result = null;
        try {
            result = mock.perform(RESTGetComum(path, vars).params(params));
        } catch (Exception e) {
            throw ExceptionUtils.convertToGeneric(e);
        }
        return result;
    }

    public static ResultActions performPost(MockMvc mock, String apiDir, Object obj) throws GenericException {
        ResultActions result = null;
        try {
            result = mock.perform(post(apiDir).contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(obj)));
        } catch (Exception e) {
            throw ExceptionUtils.convertToGeneric(e);
        }
        return result;
    }

    public static ResultActions performDelete(MockMvc mock, String path, Object... vars) throws GenericException {
        ResultActions result = null;
        try {
            result = mock.perform(delete(path, vars).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(new byte[0]));
        } catch (Exception e) {
            throw ExceptionUtils.convertToGeneric(e);
        }
        return result;
    }

    public static ResultActions performPut(MockMvc mock, String apiDir, Object obj) throws GenericException {
        ResultActions result = null;
        try {
            result = mock.perform(put(apiDir).contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(obj)));
        } catch (Exception e) {
            throw ExceptionUtils.convertToGeneric(e);
        }
        return result;
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
	
    public static PessoaJuridicaCadastroDTO getDefaultPessoaJuridicaCadastroDTO() {
	  return new PessoaJuridicaCadastroDTO()
	            .setCnpj(DEFAULT_VALID_CNPJ)
	            .setNomeFantasia(DEFAULT_STRING_TAM_9)
	            .setRazaoSocial(DEFAULT_STRING_TAM_9)
	            .setRazaoSocial(DEFAULT_STRING_TAM_9)
	            .setSigla(DEFAULT_STRING_TAM_9);
	}

    public static PessoaJuridicaCadastroDTO getPJCadastroWithId() {
        return getDefaultPessoaJuridicaCadastroDTO().setId(DEFAULT_INVALID_ID);
    }

    public static PessoaJuridicaCadastroDTO getPJCadastroWithCNPJExistent() {
        return getDefaultPessoaJuridicaCadastroDTO();
    }

    public static PessoaJuridicaCadastroDTO getPJCadastroWithInvalidCNPJ() {
        return getDefaultPessoaJuridicaCadastroDTO().setCnpj(INVALID_CNPJ);
    }

}
