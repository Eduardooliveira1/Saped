package br.gov.mme.service.util;

import java.util.InputMismatchException;
import java.util.logging.Logger;

public final class ValidatorUtils {

    private static Logger LOGGER;

    private ValidatorUtils() {

    }

    // "throws" - protege o código para eventuais erros de conversao de tipo (int)
    public static boolean cnpjValido(String cnpj) {
        // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (verificaDigitosIguais(cnpj)) {
            return (false);
        }

        char dig13, dig14;
        int r;
        try {
            // Calculo do 1o. Digito Verificador

            r = calculaPeso(cnpj, 11);
            dig13 = getDigVerificador(r);

            // Calculo do 2o. Digito Verificador

            r = calculaPeso(cnpj, 12);
            dig14 = getDigVerificador(r);
            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
                return (true);
            }
        } catch (InputMismatchException erro) {
            LOGGER.throwing("ValidatorUtils", "cnpjValido", erro);
        }
        return (false);
    }

    private static char getDigVerificador(int r) {
        char dig14;
        if ((r == 0) || (r == 1)) {
            dig14 = '0';
        } else {
            dig14 = (char) ((11 - r) + 48);
        }
        return dig14;
    }

    private static boolean verificaDigitosIguais(String cnpj) {
        for (char c : cnpj.toCharArray()) {
            if (c != cnpj.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    private static int convertePeso(int peso) {
        peso++;
        if (peso == 10) {
            return 2;
        }
        return peso;
    }

    private static int calculaPeso(String cnpj, int pos) {
        int sm = 0, peso = 2;
        for (int i = pos; i >= 0; i--) {
            int num = (int) (cnpj.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = convertePeso(peso);
        }
        return sm % 11;
    }

}