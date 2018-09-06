package br.gov.mme.config.security;

import br.gov.mme.config.Constants;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.authentication.AbstractLdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;


/**
 * Classe criada para permitir a configuração do AD usando uma Role específica: {@link Constants#USUARIO_INTERNO}
 */
public class SAPEDActiveDirectoryLdapAuthenticationProvider extends AbstractLdapAuthenticationProvider {

    private ActiveDirectoryLdapAuthenticationProvider provider;

    public SAPEDActiveDirectoryLdapAuthenticationProvider(String domain, String url) {
        provider = new ActiveDirectoryLdapAuthenticationProvider(domain, url);
    }

    @Override
    protected DirContextOperations doAuthentication(UsernamePasswordAuthenticationToken auth) {
        try {
            return (DirContextOperations) MethodUtils.invokeMethod(provider, true, "doAuthentication", auth);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.debug("Erro ao realizar Login AD", e);
            throw  new BadCredentialsException("Erro ao realizar login no AD", e.getCause());
        }
    }

    @Override
    protected Collection<? extends GrantedAuthority> loadUserAuthorities(DirContextOperations userData, String username, String password) {
        return Collections.singletonList(new SimpleGrantedAuthority(Constants.USUARIO_INTERNO));
    }

    public void setSearchFilter(String searchFilter) {
        provider.setSearchFilter(searchFilter);
    }

    public void setConvertSubErrorCodesToExceptions(Boolean adConvertSubError) {
        provider.setConvertSubErrorCodesToExceptions(adConvertSubError);
    }
}
