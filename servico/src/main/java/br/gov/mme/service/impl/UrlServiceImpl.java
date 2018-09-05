package br.gov.mme.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.gov.mme.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService {

    private final String frontUrl;

    @Autowired
    public UrlServiceImpl(@Value("${application.front-url}") String frontUrl) {
        this.frontUrl = frontUrl;
    }

    @Override
    public String getFrontUrl() {
        return this.frontUrl;
    }

}
