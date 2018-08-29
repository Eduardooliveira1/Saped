package br.gov.mme.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Saped.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String localAddress;

    private final Ldap ldap = new Ldap();

    public Ldap getLdap() {
        return ldap;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public static class Ldap {

        private String url;
        private String searchBase;
        private String searchFilter;
        private String passwordAttribute;

        public String getUrl() {
            return url;
        }

        public String getSearchBase() {
            return searchBase;
        }

        public String getSearchFilter() {
            return searchFilter;
        }

        public String getPasswordAttribute() {
            return passwordAttribute;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setSearchBase(String searchBase) {
            this.searchBase = searchBase;
        }

        public void setSearchFilter(String searchFilter) {
            this.searchFilter = searchFilter;
        }

        public void setPasswordAttribute(String passwordAttribute) {
            this.passwordAttribute = passwordAttribute;
        }
    }

}
