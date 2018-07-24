import {HttpService} from '@basis/angular-components';
import {environment} from './../../environments/environment.prod';
import {Injectable} from '@angular/core';
import {Pageable} from '../util/pageable-request';
import {RequestOptions} from '@angular/http';
import {HttpParams} from '@angular/common/http';

@Injectable()
export class PessoaJuridicaService {

    resourceUrl = environment.apiUrl + '/pessoas-juridicas';

    constructor(private http: HttpService) {}

    listarDirigentes(filtro: string, pageable: Pageable, callback?: any) {
        const options = new RequestOptions({params: pageable});
        if (filtro) {
            options.params.append('query', filtro);
        }
        return this.http.get(this.resourceUrl, options);
    }

    apagarPessoa(id) {
        this.http.post(this.resourceUrl + '/exlcuir/{id}', {
            params: new HttpParams().set('id', id)
        } );
    }
}
