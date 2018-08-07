import { environment } from '../../environments/environment.prod';
import { Injectable } from '@angular/core';
import {Pageable} from '../util/pageable-request';
import {RequestOptions} from '@angular/http';
import {HttpService} from '@basis/angular-components';

@Injectable()
export class RelatoriosService {

    listPagamentosUrl = environment.apiUrl + '/pagamentos';

    constructor(private http: HttpService) {
    }

    listarPagamentos(pageable: Pageable) {
        const options = new RequestOptions({ params: pageable });
        return this.http.get(this.listPagamentosUrl, options);
    }
}