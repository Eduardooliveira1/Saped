import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'descrisaoStatusBoleto' })
export class StatusBoletoIdToDescricaoPipe implements PipeTransform {
  transform(value: any) {
    let descricao = value;
    switch (value) {
      case 'AV': {
        descricao = 'A Vencer';
        break;
      }
      case 'AD': {
        descricao = 'adiantado';
        break;
      }
      case 'EM': {
        descricao = 'Emitido';
        break;
      }
      case 'PG': {
        descricao = 'Pago';
        break;
      }
      case 'VE': {
        descricao = 'Vencido';
        break;
      }
      default : {
        descricao = '';
      }
    }
    return descricao;
  }
}
