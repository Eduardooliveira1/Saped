package br.gov.mme.config;

import br.gov.mme.config.security.SAPEDMMEAuthenticationConfig;
import br.gov.mme.security.AuthoritiesConstants;
import br.gov.mme.security.jwt.JWTConfigurer;
import br.gov.mme.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.annotation.PostConstruct;

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
     *
     */
    @Bean
    public SAPEDMMEAuthenticationConfig apiAuthenticationConfig() {
        return auth -> {
            try {
                auth.ldapAuthentication()
                        .userSearchBase(applicationProperties.getLdap().getSearchBase())
                        .userSearchFilter(applicationProperties.getLdap().getSearchFilter())
                        .contextSource().url(applicationProperties.getLdap().getUrl())
                        .managerDn("cn=admin")
                        .managerPassword("admin")
                        .and().passwordCompare().passwordAttribute(applicationProperties.getLdap().getPasswordAttribute());
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
//            auth.authenticationProvider(daoAuthenticationProvider);
        };
    }

    /**
     * Configuração de autenticação para desenvolviento.
     *
     * @return SAPEDMMEAuthenticationConfig
     */
    @Bean
    @Primary
    @Profile("dev")
    public SAPEDMMEAuthenticationConfig apiAuthenticationConfigDev() {
        return auth -> {
            try {
                auth.inMemoryAuthentication().withUser("felipe").password("password").authorities(AuthoritiesConstants.ADMIN);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        };
    }

}
