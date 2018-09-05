package br.gov.mme.web.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.SapedApp;
import br.gov.mme.domain.Boleto;
import br.gov.mme.domain.StatusBoleto;
import br.gov.mme.enumeration.FlSituacaoBancaria;
import br.gov.mme.enumeration.TpBoleto;
import br.gov.mme.enumeration.TpStatusBoleto;
import br.gov.mme.exceptions.FiltroVazioException;
import br.gov.mme.repository.BoletoRepository;
import br.gov.mme.repository.PessoaJuridicaRepository;
import br.gov.mme.repository.StatusBoletoRepository;
import br.gov.mme.service.dto.BoletoRelatorioPagamentoFiltroDTO;
import br.gov.mme.service.impl.BoletoServiceImpl;
import br.gov.mme.utils.TestUtils;
import br.gov.mme.web.rest.errors.ExceptionTranslator;

@SpringBootTest(classes = SapedApp.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@TestInstance(Lifecycle.PER_CLASS)
public class RelatorioResourceIntTest {

    @Autowired
    BoletoServiceImpl boletoServiceImpl;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private StatusBoletoRepository statusBoletoRepositoy;

    @Autowired
    private BoletoRepository boletoRepository;

    @Autowired
    private EntityManager em;

    private static MockMvc restRelatorioMockMvc;

    private static final String API = "/api/relatorios";

    private static final String LIST_PAGAMENTOS = API + "/pagamentos";

    private static final String BOLETO_ENTITY = "boleto";
    
    private static final String OTHER_CNPJ = "00000000000001";

    private static final String[] FILTRO_RELATORIO_PAGAMENTO_TEST_NAMES = { "Data Vencimento", 
            "Valor Boleto",  "Mes Referencia", "Status Boleto" };

    private static BoletoRelatorioPagamentoFiltroDTO getFiltroRelatorioPagamentoListAll(Boolean hasFiltro) {
        BoletoRelatorioPagamentoFiltroDTO filtroPagamentoRelatorioDTO = new BoletoRelatorioPagamentoFiltroDTO();
        filtroPagamentoRelatorioDTO.setHasFiltro(hasFiltro);
        return filtroPagamentoRelatorioDTO;
    }

    private BoletoRelatorioPagamentoFiltroDTO[] getPossibleFiltrosRelatorioPagamento() {
        BoletoRelatorioPagamentoFiltroDTO filtroDataVen = new BoletoRelatorioPagamentoFiltroDTO();
        filtroDataVen.setDataVencimento(TestUtils.DEFAULT_DATE_STRING_YYYY_MM_DD);
        filtroDataVen.setHasFiltro(Boolean.TRUE);

        BoletoRelatorioPagamentoFiltroDTO filtroValor = new BoletoRelatorioPagamentoFiltroDTO();
        filtroValor.setValor(TestUtils.UPDATED_BIGDECIMAL_TAM_9_0);
        filtroValor.setHasFiltro(Boolean.TRUE);

        BoletoRelatorioPagamentoFiltroDTO filtroMesReferencia = new BoletoRelatorioPagamentoFiltroDTO();
        filtroMesReferencia.setMesReferencia(TestUtils.DEFAULT_INTEGER_TAM_2);
        filtroMesReferencia.setHasFiltro(Boolean.TRUE);

        BoletoRelatorioPagamentoFiltroDTO filtroStatusBoleto = new BoletoRelatorioPagamentoFiltroDTO();
        filtroStatusBoleto.setTpStatusBoleto(TpStatusBoleto.EM.getId());
        filtroStatusBoleto.setHasFiltro(Boolean.TRUE);

        BoletoRelatorioPagamentoFiltroDTO[] filtros = { filtroDataVen, filtroValor,
                filtroMesReferencia, filtroStatusBoleto };

        return filtros;
    }

    private void resultActionForRelatorioPagamentoFiltro(final int pos, 
            BoletoRelatorioPagamentoFiltroDTO filtro, ResultActions result) throws Exception {
        switch (pos) {
        case (0):
            result.andExpect(jsonPath("$.content[0].dataVencimento")
                    .value(TestUtils.DEFAULT_LOCAL_DATE
                            .format(TestUtils.DATE_FORMATTER_dd_MM_YYYY)));
            break;
        case (1):
            result.andExpect(jsonPath("$.content[0].valorBoleto")
                    .value(filtro.getValor()));
            break;
        case (2):
            result.andExpect(jsonPath("$.content[0].mesReferencia")
                    .value(TestUtils.MES_REF_NOV_2222));
            break;
        case (3):
            result.andExpect(jsonPath("$.content[0].statusBoleto")
                    .value(filtro.getTpStatusBoleto()));
            break;
        }
    }

    private static BoletoRelatorioPagamentoFiltroDTO getFullFiltroRelatorioPagamento() {
        BoletoRelatorioPagamentoFiltroDTO filtro = new BoletoRelatorioPagamentoFiltroDTO();
        filtro.setDataVencimento(TestUtils.UPDATED_DATE_STRING_YYYY_MM_DD);
        filtro.setValor(TestUtils.UPDATED_BIGDECIMAL_TAM_9_0);
        filtro.setMesReferencia(TestUtils.DEFAULT_INTEGER_TAM_2);
        filtro.setTpStatusBoleto(TpStatusBoleto.VE.getId());
        filtro.setHasFiltro(Boolean.TRUE);

        return filtro;
    }

    private void setMultipleBoletosForRelatorioPagamentos() {
        Boleto boletoId = TestUtils.getBoletoWithCustomPJEntity(em, TestUtils.DEFAULT_VALID_CNPJ);
        boletoId.setDataVencimento(TestUtils.DEFAULT_LOCAL_DATE);
        this.boletoRepository.saveAndFlush(boletoId);

        Boleto boletoDiferentValor = TestUtils.getBoletoWithCustomPJEntity(em,
                TestUtils.UPDATED_VALID_CNPJ);
        boletoDiferentValor.setValorBoleto(TestUtils.UPDATED_BIGDECIMAL_TAM_9_0);
        this.boletoRepository.saveAndFlush(boletoDiferentValor);

        Boleto boletoDiferentMesReferencia = TestUtils
                .getBoletoWithAnoAndMesReferenciaAndCustomPJCNPJEnity(this.em, TestUtils.INVALID_CNPJ);
        this.boletoRepository.saveAndFlush(boletoDiferentMesReferencia);
        
        Boleto boletoDiferentStatus = TestUtils.getBoletoWithPJCustomCNPJAndOtherStatusEntity(
                em, OTHER_CNPJ);
        this.boletoRepository.saveAndFlush(boletoDiferentStatus);
    }

    @BeforeEach
    public void setup() {
        RelatorioResource relatorioResource = new RelatorioResource(boletoServiceImpl);

        restRelatorioMockMvc = TestUtils.setupMockMvc(relatorioResource, pageableArgumentResolver,
                jacksonMessageConverter, exceptionTranslator);

        TestUtils.deleteAll(this.statusBoletoRepositoy, this.boletoRepository, this.pessoaJuridicaRepository);
    }

    @Test
    @Transactional
    public void listAllPagamentos() throws Exception {
        Boleto boleto = TestUtils.getDefaultBoletoEntity(em);
        this.boletoRepository.saveAndFlush(boleto);
        StatusBoleto statusBoleto = statusBoletoRepositoy.findAll().get(0);
        TestUtils.performPost(restRelatorioMockMvc, LIST_PAGAMENTOS, 
            getFiltroRelatorioPagamentoListAll(Boolean.FALSE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].cnpj").value(boleto.getPessoaJuridica().getCnpj()))
                .andExpect(jsonPath("$.content[0].nomeFantasia").value(boleto.getPessoaJuridica()
                    .getNomeFantasia()))
                .andExpect(jsonPath("$.content[0].valorBoleto").value(boleto.getValorBoleto()))
                .andExpect(jsonPath("$.content[0].mesReferencia").value(boleto.getMesReferencia()))
                .andExpect(jsonPath("$.content[0].dataVencimento")
                    .value(boleto.getDataVencimento().format(TestUtils.DATE_FORMATTER_dd_MM_YYYY)))
                .andExpect(jsonPath("$.content[0].statusBoleto").value(statusBoleto.getTpStatusBoleto()
                    .getId()))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.first").exists())
                .andExpect(jsonPath("$.size").isNotEmpty())
                .andExpect(jsonPath("$.number").exists());
    }

    @Test
    @Transactional
    public void listPagamentosWithFiltroVazioException() throws Exception {
        TestUtils.performPostWithExceptions(restRelatorioMockMvc, LIST_PAGAMENTOS,
                getFiltroRelatorioPagamentoListAll(Boolean.TRUE), FiltroVazioException.MESSAGE, BOLETO_ENTITY);
    }
    
    @TestFactory
    @Transactional
    public Stream<DynamicTest> listPagamentosMultipleFiltros() {
        List<BoletoRelatorioPagamentoFiltroDTO> inputList = Arrays.asList(
                this.getPossibleFiltrosRelatorioPagamento());

        this.setMultipleBoletosForRelatorioPagamentos();

        return inputList.stream().map(filtro -> DynamicTest
                .dynamicTest("Teste filtro: " + FILTRO_RELATORIO_PAGAMENTO_TEST_NAMES[inputList.indexOf(filtro)],
                        () -> {
                            int pos = inputList.indexOf(filtro);
                            this.resultActionForRelatorioPagamentoFiltro(pos, filtro,
                                    TestUtils.performPost(restRelatorioMockMvc, LIST_PAGAMENTOS, filtro)
                                            .andExpect(status().isOk())
                                            .andExpect(jsonPath("$.totalElements").value(1)));
                            }
                        )
                );
    }

    @Test
    @Transactional
    @SuppressWarnings("unused")
    public void listPagamentosWithFiltroId() throws Exception {
        BoletoRelatorioPagamentoFiltroDTO filtroId = new BoletoRelatorioPagamentoFiltroDTO();
        filtroId.setHasFiltro(Boolean.TRUE);

        Boleto boleto = TestUtils.getBoletoWithCustomPJEntity(this.em, TestUtils.DEFAULT_VALID_CNPJ);
        Boleto otherBoleto = TestUtils.getBoletoWithCustomPJEntity(this.em, TestUtils.UPDATED_VALID_CNPJ);
        Boleto boletoNotReturned = TestUtils.getBoletoWithCustomPJEntity(this.em, OTHER_CNPJ);

        filtroId.setIdsPessoasJuridicas(Arrays.asList(boleto.getPessoaJuridica().getId(),
                otherBoleto.getPessoaJuridica().getId()));
        TestUtils.performPost(restRelatorioMockMvc, LIST_PAGAMENTOS, filtroId).andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].cnpj").value(boleto.getPessoaJuridica().getCnpj()))
            .andExpect(jsonPath("$.content[1].cnpj").value(otherBoleto.getPessoaJuridica().getCnpj()))
            .andExpect(jsonPath("$.totalElements").value(2));
    }

    @SuppressWarnings("unused")
    @Test
    @Transactional
    public void listPagamentosWithFullFiltro() throws Exception {
        BoletoRelatorioPagamentoFiltroDTO fullFiltro = getFullFiltroRelatorioPagamento();

        Boleto boletoDontMatchAll = TestUtils.getDefaultBoletoEntity(this.em);
        Boleto boletoExpected = TestUtils.getDiferentBoletoEntity(this.em);

        Boleto boletoMatchSome = BoletoResourceIntTest.getEntity(this.em, null, TestUtils.UPDATED_LOCAL_DATE,
                TestUtils.DEFAULT_BIGDECIMAL_TAM_9_0, TestUtils.DEFAULT_LONG_TAM_6, TpBoleto.AD,
                TestUtils.getPessoaJuridicaWithCustomCNPJEntity(this.em, OTHER_CNPJ),
                FlSituacaoBancaria.BOLETO_EMITIDO, TestUtils.LOCAL_DATE_TIME_NOW, TpStatusBoleto.VE, null, null);
        this.boletoRepository.saveAndFlush(boletoMatchSome);

        fullFiltro.setIdsPessoasJuridicas(
                Arrays.asList(boletoExpected.getPessoaJuridica().getId(), boletoMatchSome.getPessoaJuridica().
                        getId()));

        TestUtils.performPost(restRelatorioMockMvc, LIST_PAGAMENTOS, fullFiltro).andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].cnpj").value(boletoExpected.getPessoaJuridica().getCnpj()))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

}
