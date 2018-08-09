package br.gov.mme.service.impl;
import br.gov.mme.service.QuintoDiaUtilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class QuintoDiaUtilServiceImpl implements QuintoDiaUtilService {

    @Override
    public List<String> obterQuintosDiasUteis(int ano){

        int contadorDeDiasUteis = 0;
        List<String> quintosDiasUteis = new ArrayList<>();
        Calendar calendario = Calendar.getInstance();
        for (int mes = 1; mes <= 12; mes++) {
            for(int dia = 1; dia <= 31 ; dia++) {
                calendario = setaDia(calendario, dia, mes, ano);
                if((calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
                        && (calendario.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
                    contadorDeDiasUteis++;
                    if(contadorDeDiasUteis == 5) {
                        quintosDiasUteis.add(converteDataParaString(calendario.getTime()));
                        break;
                    }
                }
            }
            contadorDeDiasUteis = 0;
        }
        return quintosDiasUteis;
     }

     private Calendar setaDia( Calendar calendario, int dia, int mes, int ano) {
         calendario.set(Calendar.YEAR, ano);
         calendario.set(Calendar.MONTH, mes-1 );
         calendario.set(Calendar.DAY_OF_MONTH, dia);
         return calendario;
     }

    private String converteDataParaString(Date data)
    {
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
        return sdfr.format( data );
    }
}
