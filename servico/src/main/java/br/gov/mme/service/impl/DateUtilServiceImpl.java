package br.gov.mme.service.impl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.mme.service.DateUtilService;
import br.gov.mme.service.dto.EnumerationDTO;
import br.gov.mme.service.util.DateUtils;

@Service
public class DateUtilServiceImpl implements DateUtilService {

    @Override
    public List<EnumerationDTO> listarMesReferencia() {
        List<EnumerationDTO> mesReferencia = new ArrayList<>();
        for (int i = Month.JANUARY.getValue() - 1; i < Month.DECEMBER.getValue(); i++) {
            mesReferencia.add(new EnumerationDTO(String.valueOf(i),
                    DateUtils.convertMesAnoReferenciaToString(i, LocalDate.now().getYear())));
        }
        return mesReferencia;
    }

}
