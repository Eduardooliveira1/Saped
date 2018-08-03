package br.gov.mme.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mme.service.BoletoService;

/**
 * Service Implementation for managing Boleto.
 */
@Service
@Transactional
public class BoletoServiceImp implements BoletoService{
    
//    @Override
//    public FileOutputStream getExportFile() throws FileException {
//        try {
//            return new FileOutputStream(file);
//        } catch (FileNotFoundException e) {
//            log.error(e.getMessage(), e);
//            throw new FileException(e);
//        }
//    }

}
