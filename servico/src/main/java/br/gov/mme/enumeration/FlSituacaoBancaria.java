package br.gov.mme.enumeration;

import br.gov.mme.util.EnumUtil;

public enum FlSituacaoBancaria {

    PAGAMENTO_EFETUADO("00", "Pagamento efetuado"),

    PAGAMENTO_NAO_AUTORIZADO("01", "Pagamento não autorizado/transação recusada"),

    ERRO_PROCESSAMENTO_02("02", EnumUtil.ERRO_PROCESSAMENTO_CONSULTA),

    PAGAMENTO_NAO_LOCALIZADO("03", "Pagamento não localizado"),

    ID_CONV_NAO_VALIDO("10","Campo “idConv” inválido ou nulo"),

    VALOR_NAO_VALIDO("11","valor informado é inválido, nulo ou não confere com o valor registrado"),

    PAGAMENTO_WEB_NAO_AUTORIZADO("21", "Pagamento Web não autorizado"),

    ERRO_PROCESSAMENTO_22("22",EnumUtil.ERRO_PROCESSAMENTO_CONSULTA),

    ERRO_PROCESSAMENTO_23("23", EnumUtil.ERRO_PROCESSAMENTO_CONSULTA),

    CONVENIO_NAO_CADASTRADO("24", "Convênio não cadastrado"),

    CONVENIO_INATIVO("25","Convênio não ativo"),

    CONVENIO_NAO_PERMITE_DEBITO("26", "Convênio não permite debito em conta"),

    SERVICO_INVALIDO("27", "Serviço inválido"),

    BOLETO_EMITIDO("28", "Boleto emitido"),

    PAGAMENTO_NAO_EFETUADO("29", "pagamento não efetuado"),

    ERRO_PROCESSAMENTO_30("30", EnumUtil.ERRO_PROCESSAMENTO_CONSULTA),

    OPERACAO_CANCELADA("99","Operação cancelada pelo cliente");

    private String numeroOp;

    private String descricao;

    FlSituacaoBancaria(String numeroOp, String descricao) {
        this.numeroOp = numeroOp;
        this.descricao = descricao;
    }
    
    public String numeroOp() {
        return this.numeroOp;
    }

    public String descricao() {
        return this.descricao;
    }

}
