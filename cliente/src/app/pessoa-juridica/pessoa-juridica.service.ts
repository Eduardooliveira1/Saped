import {HttpService} from '@basis/angular-components';
import {HttpClient} from '@angular/common/http';
import {environment} from './../../environments/environment.prod';
import {Injectable} from '@angular/core';
import {Pageable} from '../util/pageable-request';
import {RequestOptions} from '@angular/http';
import {ResourceMethodStrict} from 'ng-resource';
import {Path} from 'ngx-http-rest';
import { GET, PathParam } from 'ngx-http-rest';


@Injectable()
@Path(environment.apiUrl + '/pessoas-juridicas')
export class PessoaJuridicaService {

    resourceUrl = environment.apiUrl + '/pessoas-juridicas';

    constructor(private http: HttpService,
                private httpClient: HttpClient) {}

    listarDirigentes(filtro: string, pageable: Pageable, callback?: any) {
        const options = new RequestOptions({params: pageable});
        if (filtro) {
            options.params.append('query', filtro);
        }
        return this.http.get(this.resourceUrl, options);
    }

    @GET
    @Path(':id')
    apagarPessoa(@PathParam('id') id: number): any {}
}
