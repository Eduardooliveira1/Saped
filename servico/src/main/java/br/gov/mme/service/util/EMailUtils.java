package br.gov.mme.service.util;

public final class EMailUtils {

    private EMailUtils() {
    }

    public static final String REDEFINIR_LOGIN_EMAIL_HEADER = "Redefinir Senha SAPED";

    public static String getRedefinirSenhaEMailMessage(String cnpj, String url) {
        String paramURl = url + "/redefinir-senha?cnpj=" + cnpj;
        return "Solicitação de Alteração de Senha do sistema saped foi realizada para esse e-mail.\n"
                + "Favor acessar o link abaixo e redefinir a senha:\n " + paramURl
                + "\nCaso não tenha feito essa solicitação," + "favor desconsidear esse e-mail. \n";
    }

}
