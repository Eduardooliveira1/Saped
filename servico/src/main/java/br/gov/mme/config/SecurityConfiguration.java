package br.gov.mme.config;

import br.gov.mme.config.security.SAPEDMMEAuthenticationConfig;
import br.gov.mme.exceptions.EncapsuladaException;
import br.gov.mme.security.AuthoritiesConstants;
import br.gov.mme.security.jwt.JWTConfigurer;
import br.gov.mme.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Collections;

@Configuration
@Import(SecurityProblemSupport.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    private final TokenProvider tokenProvider;

    private final CorsFilter corsFilter;

    private final ApplicationProperties applicationProperties;

    private final SecurityProblemSupport problemSupport;

    public SecurityConfiguration(CorsFilter corsFilter,
                                 TokenProvider tokenProvider,
                                 ApplicationProperties applicationProperties,
                                 SecurityProblemSupport problemSupport) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.applicationProperties = applicationProperties;
        this.problemSupport = problemSupport;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/app/**/*.{js,html}").antMatchers("/i18n/**")
                .antMatchers("/content/**").antMatchers("/swagger-ui/index.html").antMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
                .and().csrf().disable().headers().frameOptions().disable().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/profile-info").permitAll()
                .antMatchers("/management/health").permitAll()
                .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/v2/api-docs/**").permitAll()
                .antMatchers("/swagger-resources/configuration/ui").permitAll()
                .antMatchers("/swagger-ui/index.html").hasAuthority(AuthoritiesConstants.ADMIN)
                .anyRequest().authenticated()
                .and().apply(securityConfigurerAdapter());
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }


    /**
     * Configurar o tipo de Autenticação.
     *
     * @param auth {@link AuthenticationManagerBuilder}
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, SAPEDMMEAuthenticationConfig pAuthenticationConfig) {
        pAuthenticationConfig.configProvider(auth);
    }

    /**
     * Configuração de autenticação de produção
     *
     * @return SAPEDMMEAuthenticationConfig
     */
    @Bean
    @ConditionalOnProperty(prefix = "application.ldap", name = "tipo", havingValue = "OPENLDAP")
    public SAPEDMMEAuthenticationConfig apiAuthenticationConfig(DataSource pDataSource) {

        return auth -> {
            try {
                authLdapAutentication(auth);
            } catch (EncapsuladaException e) {
                LOGGER.error(e.getMessage(), e);
            }
            try {
                jdbcAuthentication(pDataSource, auth);
            } catch (EncapsuladaException e) {
                LOGGER.error(e.getMessage(), e);
            }
        };
    }

    /**
     * Configuração de autenticação de produção
     *
     * @return SAPEDMMEAuthenticationConfig
     */
    @Bean
    @ConditionalOnProperty(prefix = "application.ldap", name = "tipo", havingValue = "AD")
    public SAPEDMMEAuthenticationConfig apiAuthenticationConfigAD(DataSource pDataSource) {
        return auth -> {
            ActiveDirectoryLdapAuthenticationProvider adProvider = new ActiveDirectoryLdapAuthenticationProvider(applicationProperties.getLdap().getSearchBase(), applicationProperties.getLdap().getUrl());
            adProvider.setSearchFilter(applicationProperties.getLdap().getSearchFilter());
            adProvider.setConvertSubErrorCodesToExceptions(applicationProperties.getLdap().getAdConvertSubError());
            adProvider.setUseAuthenticationRequestCredentials(applicationProperties.getLdap().getAdUseAuthenticationRequestCredentials());
            auth.authenticationProvider(adProvider);
            try {
                jdbcAuthentication(pDataSource, auth);
            } catch (EncapsuladaException e) {
                LOGGER.error(e.getMessage(), e);
            }
        };
    }

    private void jdbcAuthentication(DataSource pDataSource, AuthenticationManagerBuilder auth) throws EncapsuladaException {
        try {
            auth.jdbcAuthentication().dataSource(pDataSource).usersByUsernameQuery("select co_Cnpj as username, ds_Senha_Acesso as password, 1 as enabled from tb_Pessoa_juridica where co_Cnpj = ?")
                    .authoritiesByUsernameQuery(usuarioExternoQuery());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new EncapsuladaException(e);
        }
    }

    private String usuarioExternoQuery() {
        StringBuilder userQuery = new StringBuilder();
        userQuery.append("select no_Razao_Social as username, ");
        userQuery.append("'EXTERNO'");
        userQuery.append(" as role from tb_Pessoa_juridica where co_Cnpj = ?");
        return userQuery.toString();
    }

    private void authLdapAutentication(AuthenticationManagerBuilder auth) throws EncapsuladaException {
        try {
            auth.ldapAuthentication().userSearchBase(applicationProperties.getLdap().getSearchBase())
                    .userSearchFilter(applicationProperties.getLdap().getSearchFilter())
                    .groupSearchBase(applicationProperties.getLdap().getGroupBase())
                    .groupSearchFilter(applicationProperties.getLdap().getGroupFilter())
                    .contextSource().url(applicationProperties.getLdap().getUrl())
                    .managerDn(applicationProperties.getLdap().getManagerDn())
                    .managerPassword(applicationProperties.getLdap().getManagerPassword())
                    .and().userDetailsContextMapper(new LdapUserDetailsMapper() {
                @Override
                public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
                    return super.mapUserFromContext(ctx, username, Collections.singletonList(new SimpleGrantedAuthority(Constants.USUARIO_EXTERNO)));
                }
            }).passwordCompare().passwordEncoder(new LdapShaPasswordEncoder()).passwordAttribute(applicationProperties.getLdap().getPasswordAttribute());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new EncapsuladaException(e);
        }
    }

}
