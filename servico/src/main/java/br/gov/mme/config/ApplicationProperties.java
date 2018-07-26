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

    private final Ip ip = new Ip();

    private final Url url = new Url();

    public Ip getIp() {
        return ip;
    }

    public Url getUrl() {
        return url;
    }

    public static class Ip {

        private String localAddress;

        public String getLocalAddress() {
            return localAddress;
        }

        public void setLocalAddress(String localAddress) {
            this.localAddress = localAddress;
        }

    }

    public static class Url {

        private String ldap;


        public String getLdap() {
            return ldap;
        }

        public void setLdap(String ldap) {
            this.ldap = ldap;
        }
    }

}
