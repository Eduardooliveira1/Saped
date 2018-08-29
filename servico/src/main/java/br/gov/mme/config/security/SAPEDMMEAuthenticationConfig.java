package br.gov.mme.config.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * Interface apra configuração do tipo de Autenticação, DEV ou PROD
 */
public interface SAPEDMMEAuthenticationConfig {

    /**
     * Configurar Login
     *
     * @param auth {@link AuthenticationManagerBuilder}
     * @throws Exception Erros de configuração
     */
    void configProvider(AuthenticationManagerBuilder auth);
}
