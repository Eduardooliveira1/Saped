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
        private String groupBase;
        private String groupFilter;
        private String managerDn;
        private String managerPassword;
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

        public String getGroupBase() {
            return groupBase;
        }

        public void setGroupBase(String groupBase) {
            this.groupBase = groupBase;
        }

        public String getGroupFilter() {
            return groupFilter;
        }

        public void setGroupFilter(String groupFilter) {
            this.groupFilter = groupFilter;
        }

        public String getManagerDn() {
            return managerDn;
        }

        public void setManagerDn(String managerDn) {
            this.managerDn = managerDn;
        }

        public String getManagerPassword() {
            return managerPassword;
        }

        public void setManagerPassword(String managerPassword) {
            this.managerPassword = managerPassword;
        }
    }

}
