import {Pipe, PipeTransform} from '@angular/core';
import {RelatorioPagamentoList} from '../../relatorios/pagamentos/relatorio-pagamento-list';

@Pipe({name: 'boletoDataVencimentoPipe' })
export class BoletoDataVencimentoPipe implements PipeTransform {
  transform(value: RelatorioPagamentoList) {
    if (value.tpBoleto === 'SV') {
      return value.dataVencimento;
    }
    return '-';
  }
}
