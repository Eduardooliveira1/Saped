import {Injectable} from '@angular/core';
import {RequestOptions, Response} from '@angular/http';
import {HttpService} from '@basis/angular-components';
import {Observable} from '../../../node_modules/rxjs/Observable';
import {environment} from '../../environments/environment.prod';
import {Pageable} from '../util/pageable-request';
import {FiltroRelatorioPagamentos} from './pagamentos/filtro-relatorio-pagamento';

@Injectable()
export class RelatoriosService {

  resourceUrl = environment.apiUrl + '/relatorios';
  pagamentosUrl = this.resourceUrl + '/pagamentos';
  listNomesPessoasJuridicas = this.resourceUrl + '/pessoas-juridicas';
  exportPagamentos = this.pagamentosUrl + '/exportar';

  private static convert(filtro: any): any {
    return Object.assign({}, filtro);
  }

  constructor(private http: HttpService) {
  }

  listarPagamentosLazyLoad(pageable: Pageable) {
    const options = new RequestOptions({params: pageable});
    return this.http.get(this.pagamentosUrl, options).map((res: Response) => {
      return res.json();
    });
  }

  listarPagamentos(filtro: FiltroRelatorioPagamentos, pageable: Pageable): Observable<FiltroRelatorioPagamentos>{
    const copy = RelatoriosService.convert(filtro);
    const options = new RequestOptions({params: {pageable}});
    return this.http.post(this.pagamentosUrl + '/filtrados', copy, options).map((res: Response) => {
      return res.json();
    });
  }

  listarNomesPJs() {
    return this.http.get(this.listNomesPessoasJuridicas);
  }

}
