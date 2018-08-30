package br.gov.mme.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;

import br.gov.mme.domain.Boleto;
import br.gov.mme.domain.Pessoa;
import br.gov.mme.domain.PessoaJuridica;
import br.gov.mme.enumeration.FlSituacaoBancaria;
import br.gov.mme.enumeration.FlStatus;
import br.gov.mme.enumeration.TpBoleto;
import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.web.rest.BoletoResourceIntTest;
import br.gov.mme.web.rest.PessoaJuridicaResourceIntTest;
import br.gov.mme.web.rest.TestUtil;
import br.gov.mme.web.rest.errors.ErrorConstantsUtil;
import br.gov.mme.web.rest.errors.ExceptionTranslator;

public final class TestUtils {

	private TestUtils() {
	}

    // ATRIBUTOS:


	// CONSTANTES:

    public static final Integer DEFAULT_INTEGER_TAM_2 = 11;

    public static final Integer DEFAULT_INTEGER_TAM_4 = 2222;

    public static final Long DEFAULT_LONG_TAM_6 = 111111L;

    public static final Long UPDATED_LONG_TAM_6 = 222222L;

    public static final Long DEFAULT_INVALID_ID = 2L;

    public static final BigDecimal DEFAULT_BIGDECIMAL_TAM_3_0 = new BigDecimal(31);

    public static final BigDecimal DEFAULT_BIGDECIMAL_TAM_9_0 = new BigDecimal(123456789);

    public static final BigDecimal UPDATED_BIGDECIMAL_TAM_9_0 = new BigDecimal(987654321);

	public static final String DEFAULT_STRING_TAM_9 = "aaaaaaaaa";

	public static final String UPDATED_STRING_TAM_9 = "bbbbbbbbb";

	public static final String DEFAULT_VALID_CNPJ = "87772272000198";

	public static final String UPDATED_VALID_CNPJ = "83645050000153";

    public static final String INVALID_CNPJ = "11111111111111";

    public static final String UPDATED_DATE_STRING_YYYY_MM_DD = "2044-09-20";

    public static final String DEFAULT_DATE_STRING_YYYY_MM_DD = "2022-10-22";

    public static final String MES_REF_NOV_2222 = "Novembro/2222";

    public static final String DEFAULT_EMAIL = "dev@basis.com";

    public static final LocalDate LOCAL_DATE_NOW = LocalDate.now();

    public static final LocalDate DEFAULT_LOCAL_DATE = LocalDate.of(2022, 10, 22);

    public static final LocalDate UPDATED_LOCAL_DATE = LocalDate.of(2044, 9, 20);

	public static final LocalDateTime LOCAL_DATE_TIME_NOW = LocalDateTime.now();

    public static final LocalDateTime LOCAL_DATE_TIME_IN_2_YEARS = LocalDateTime.now().plusYears(2L);

    public static final DateTimeFormatter DATE_FORMATTER_dd_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/YYYY");

	// FUNÇÕES AUXILIARES:
	
    private static MockHttpServletRequestBuilder restGetComum(
			String path, Object... vars) {
		return get(path, vars).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
        .content(new byte[0]);
	}

    public static ResultActions exceptionResultAction(ResultActions response, 
            String exceptionMessage, String params) throws Exception {
        return response.andExpect(status().isBadRequest())
                .andExpect(header().stringValues(ErrorConstantsUtil.APP_ERROR, exceptionMessage))
                .andExpect(header().stringValues(ErrorConstantsUtil.APP_PARAMS, params));
    }

    public static ResultActions performGet(MockMvc mock, String path, Object... vars) throws Exception {
        return mock.perform(restGetComum(path, vars));
    }

    public static ResultActions performGetWithParams(MockMvc mock, String path, 
            MultiValueMap<String, String> params, Object... vars) throws Exception {
        return mock.perform(restGetComum(path, vars).params(params));
    }

    public static ResultActions performPost(MockMvc mock, String apiDir, Object obj) throws Exception {
        return mock.perform(post(apiDir).contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(obj)));
    }

    public static ResultActions performPostWithExceptions(MockMvc mock, String
    apiDir, Object obj, String exceptionMessage, String params) throws Exception {
        return exceptionResultAction(performPost(mock, apiDir, obj), exceptionMessage, params);
     }

    public static ResultActions performDelete(MockMvc mock, String path, Object... vars) throws Exception {
        return mock.perform(delete(path, vars).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(new byte[0]));
    }

    public static ResultActions performDeleteWithException(MockMvc mock, String path, String exceptionMessage,
            String params, Object... vars) throws Exception {
        return exceptionResultAction(performDelete(mock, path, vars), exceptionMessage, params);
    }

    public static ResultActions performPut(MockMvc mock, String apiDir, Object obj) throws Exception {
        return mock.perform(put(apiDir).contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(obj)));
    }

    public static ResultActions performPutWithExceptions(MockMvc mock, String apiDir, Object obj,
            String exceptionMessage, String params) throws Exception {
        return exceptionResultAction(performPut(mock, apiDir, obj), exceptionMessage, params);
    }

	public static MockMvc setupMockMvc(Object resource,
			PageableHandlerMethodArgumentResolver pageableArgumentResolver,
            MappingJackson2HttpMessageConverter jacksonMessageConverter, ExceptionTranslator exceptionTranslator) {
		return MockMvcBuilders.standaloneSetup(resource)
			.setCustomArgumentResolvers(pageableArgumentResolver)
			.setControllerAdvice(exceptionTranslator)
			.setMessageConverters(jacksonMessageConverter).build();
	}
	
    public static void deleteAll(JpaRepository<?, ?>... repositories) {
        for (JpaRepository<?, ?> repository : repositories) {
            repository.deleteAll();
            repository.flush();
        }
    }

    public static void saveAndFlush(Object entity, EntityManager em) {
        em.persist(entity);
        em.flush();
    }

    // GERADORES DE ENTIDADES:
	
	public static Pessoa getDefaultPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setDataCadastro(LOCAL_DATE_TIME_NOW);
		pessoa.setStatus(FlStatus.S);
		return pessoa;
	}
	
    public static PessoaJuridica getPessoaJuridicaWithCustomCNPJEntity(EntityManager em, String cnpj) {
        PessoaJuridica pessoaJuridica = PessoaJuridicaResourceIntTest
                .createEntity(DEFAULT_STRING_TAM_9, cnpj);
        saveAndFlush(pessoaJuridica, em);

        return pessoaJuridica;
    public static PessoaJuridicaCadastroDTO getPJCadastroWithId() {
        return getDefaultPessoaJuridicaCadastroDTO().id(DEFAULT_INVALID_ID);
    }

    public static Boleto getDefaultBoletoEntity(EntityManager em) {
        Boleto boleto = BoletoResourceIntTest.getDefaultEntity(em);
        saveAndFlush(boleto, em);

        return boleto;
    }

    public static Boleto getDiferentBoletoEntity(EntityManager em) {
        Boleto boleto = BoletoResourceIntTest.getDiferentEntity(em);
        saveAndFlush(boleto, em);

        return boleto;
    }

    public static Boleto getBoletoWithCustomPJEntity(EntityManager em, String cnpj) {
        Boleto boleto = BoletoResourceIntTest.getWithCustomCNPJEntity(em, cnpj);
        saveAndFlush(boleto, em);

        return boleto;
    }

    public static Boleto getBoletoWithAnoAndMesReferenciaAndCustomPJCNPJEnity(EntityManager em,
            String cnpj) {
        Boleto boleto = BoletoResourceIntTest.getEntity(em, null, TestUtils.LOCAL_DATE_NOW,
                TestUtils.DEFAULT_BIGDECIMAL_TAM_9_0, TestUtils.DEFAULT_LONG_TAM_6, TpBoleto.SV,
                getPessoaJuridicaWithCustomCNPJEntity(em, cnpj), FlSituacaoBancaria.BOLETO_EMITIDO,
                TestUtils.LOCAL_DATE_TIME_NOW, TpStatusBoleto.AV, TestUtils.DEFAULT_INTEGER_TAM_2,
                TestUtils.DEFAULT_INTEGER_TAM_4);
        saveAndFlush(boleto, em);

        return boleto;
    }

    public static Boleto getBoletoWithPJCustomCNPJAndOtherStatusEntity(EntityManager em, String cnpj) {
        Boleto boleto = BoletoResourceIntTest.getEntity(em, null, TestUtils.LOCAL_DATE_NOW,
                TestUtils.DEFAULT_BIGDECIMAL_TAM_9_0, TestUtils.DEFAULT_LONG_TAM_6, TpBoleto.AD,
                getPessoaJuridicaWithCustomCNPJEntity(em, cnpj), FlSituacaoBancaria.BOLETO_EMITIDO,
                TestUtils.LOCAL_DATE_TIME_NOW, TpStatusBoleto.EM, null, null);
        saveAndFlush(boleto, em);

        return boleto;
    }

}
