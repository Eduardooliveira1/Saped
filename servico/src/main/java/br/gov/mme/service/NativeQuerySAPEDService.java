package br.gov.mme.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.basis.nativequerybuilder.pojo.NativeQueryBuilder;

public interface NativeQuerySAPEDService {
    
    Page<?> filter(NativeQueryBuilder nativeQueryBuilder, 
            Pageable pageable, Boolean isExporting);

}
