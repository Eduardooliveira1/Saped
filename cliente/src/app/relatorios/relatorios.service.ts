import { HttpClient } from '@angular/common/http';
import { sapedUtil } from './../shared/metodos/sapedUtil';
import {Injectable} from '@angular/core';
import {RequestOptions, Response} from '@angular/http';
import {HttpService} from '@basis/angular-components';
import {Observable} from '../../../node_modules/rxjs/Observable';
import {environment} from '../../environments/environment.prod';
import {Page} from '../util/page';
import {Pageable} from '../util/pageable-request';
import {FiltroRelatorioPagamentos} from './pagamentos/filtro-relatorio-pagamento';
import {RelatorioPagamentoList} from './pagamentos/relatorio-pagamento-list';

@Injectable()
export class RelatoriosService {

  resourceUrl = environment.apiUrl + '/relatorios';
  pagamentosUrl = this.resourceUrl + '/pagamentos';
  listNomesPessoasJuridicas = this.resourceUrl + '/pessoas-juridicas';
  exportPagamentos = this.pagamentosUrl + '/exportar';

  private static convert(filtro: any): any {
    return Object.assign({}, filtro);
  }

  constructor(private http: HttpClient,
              private httpService: HttpService ) {
  }

  listarPagamentos(filtro: FiltroRelatorioPagamentos, pageable: Pageable): Observable<Page<RelatorioPagamentoList>> {
    const filtroCopy = RelatoriosService.convert(filtro);
    const options = new RequestOptions({params: {pageable}});
    sapedUtil.insereAutorizacaoHeader(options);

    return this.httpService.post(this.pagamentosUrl, filtroCopy, options).map((res: Response) => {
      return res.json();
    });
  }

  listarNomesPJs() {
    return this.http.get(this.listNomesPessoasJuridicas);
  }

}
