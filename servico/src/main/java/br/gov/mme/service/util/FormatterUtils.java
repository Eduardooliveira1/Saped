package br.gov.mme.service.util;

public final class FormatterUtils {

    private FormatterUtils() {
    }

    public static String converterCnpj(String cnpj) {
        return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/"
                + cnpj.substring(8, 12) + "-" + cnpj.substring(12, 14);
    }

    public static String converterNomeEmpresaToDropdown(String nomeFantasia, String cnpj) {
        return converterCnpj(cnpj) + " - " + nomeFantasia;
    }

}
