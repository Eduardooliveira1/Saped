import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs/Observable';
import { environment } from '../../environments/environment.prod';
import { Page } from '../util/page';
import { Pageable } from '../util/pageable-request';
import { sapedUtil } from '../shared/metodos/sapedUtil';
import { FiltroRelatorioPagamentos } from './pagamentos/filtro-relatorio-pagamento';
import { RelatorioPagamentoList } from './pagamentos/relatorio-pagamento-list';

@Injectable()
export class RelatoriosService {

  resourceUrl = environment.apiUrl + '/relatorios';
  pagamentosUrl = this.resourceUrl + '/pagamentos';
  listNomesPessoasJuridicas = this.resourceUrl + '/pessoas-juridicas';
  exportPagamentos = this.pagamentosUrl + '/exportar';

  private static convert(filtro: any): any {
    return Object.assign({}, filtro);
  }

  constructor(private http: HttpClient) {
  }

  listarPagamentos(filtro: FiltroRelatorioPagamentos, pageable: Pageable): Observable<Page<RelatorioPagamentoList>> {
    const filtroCopy = RelatoriosService.convert(filtro);

    let params = new HttpParams();

    params = sapedUtil.setPageableParams(pageable, params);

    return this.http.post(this.pagamentosUrl,filtroCopy, {params} ).map((res: any) => {
      return res;
    });
  }

  listarNomesPJs() {
    return this.http.get(this.listNomesPessoasJuridicas);
  }

}