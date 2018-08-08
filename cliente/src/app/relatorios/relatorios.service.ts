import { environment } from '../../environments/environment.prod';
import { Injectable } from '@angular/core';
import {Pageable} from '../util/pageable-request';
import {RequestOptions} from '@angular/http';
import {HttpService} from '@basis/angular-components';

@Injectable()
export class RelatoriosService {

    resourceUrl = environment.apiUrl + '/relatorios';
    pagamentosUrl = this.resourceUrl + '/pagamentos';
    listNomesPessoasJuridicas = this.resourceUrl + '/pessoas-juridicas';
    exportPagamentos = this.pagamentosUrl + '/exportar';

    constructor(private http: HttpService) {
    }

    listarPagamentos(pageable: Pageable) {
        const options = new RequestOptions({ params: pageable });
        return this.http.get(this.pagamentosUrl, options);
    }

    listarNomesPJs() {
        return this.http.get(this.listNomesPessoasJuridicas);
    }


}