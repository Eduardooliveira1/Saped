import {Injectable} from '@angular/core';
import {RequestOptions} from '@angular/http';
import {HttpService} from '@basis/angular-components';
import {environment} from '../../environments/environment.prod';
import {Pageable} from '../util/pageable-request';
import {FiltroRelatorioPagamentos} from './pagamentos/filtro-relatorio-pagamento';

@Injectable()
export class RelatoriosService {

  resourceUrl = environment.apiUrl + '/relatorios';
  pagamentosUrl = this.resourceUrl + '/pagamentos';
  listNomesPessoasJuridicas = this.resourceUrl + '/pessoas-juridicas';
  exportPagamentos = this.pagamentosUrl + '/exportar';

  constructor(private http: HttpService) {
  }

  listarPagamentosLazyLoad(pageable: Pageable) {
    const options = new RequestOptions({params: pageable});
    return this.http.get(this.pagamentosUrl, options);
  }

  listarPagamentos(filtro: FiltroRelatorioPagamentos, pageable: Pageable){
    const options = new RequestOptions({params: {filtro, pageable}});
    return this.http.get(this.pagamentosUrl + '/filtrados', options);
  }

  listarNomesPJs() {
    return this.http.get(this.listNomesPessoasJuridicas);
  }

}
