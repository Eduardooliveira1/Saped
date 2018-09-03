import { HttpClient } from '@angular/common/http';
import { sapedUtil } from './../shared/metodos/sapedUtil';
import { Observable } from 'rxjs/Observable';
import { HttpService } from '@basis/angular-components';
import { environment } from './../../environments/environment.prod';
import { Injectable } from "@angular/core";
import { Pageable } from '../util/pageable-request';
import { RequestOptions, Response, Headers } from '@angular/http';
import {ComunicadoCadastro} from './comunicado-cadastro.model'

@Injectable()
export class ComunicacaoService {

    resourceUrl = environment.apiUrl + "/comunicacao";
    searchUrl = environment.apiUrl + "/representantes";

    constructor(private http: HttpClient,
        private httpService: HttpService) { }

    listarRepresentantes(filtro: string, pageable: Pageable, callback?: any) {

        const options = new RequestOptions({ params: pageable });
        sapedUtil.insereAutorizacaoHeader(options);

        if (filtro) {
            options.params.append("query", filtro);
        }

        return this.httpService.get(this.searchUrl, options);
    }

    enviar(notificacao: ComunicadoCadastro): Observable<ComunicadoCadastro> {
        const copy = this.convert(notificacao);
        return this.http.post(this.resourceUrl, copy).map((res) => {
            return res;
        });
    }

    private convert(notificacao: ComunicadoCadastro): ComunicadoCadastro {
        const copy: ComunicadoCadastro = Object.assign({}, notificacao);
        return copy;
    }
}