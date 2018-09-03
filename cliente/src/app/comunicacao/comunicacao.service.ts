import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs/Observable';
import { Pageable } from '../util/pageable-request';
import { environment } from './../../environments/environment.prod';
import { sapedUtil } from './../shared/metodos/sapedUtil';
import { ComunicadoCadastro } from './comunicado-cadastro.model';

@Injectable()
export class ComunicacaoService {

    resourceUrl = environment.apiUrl + "/comunicacao";
    searchUrl = environment.apiUrl + "/representantes";

    constructor(private http: HttpClient) { }

    listarRepresentantes(filtro: string, pageable: Pageable, callback?: any) : any{

        let params = new HttpParams();
        if (filtro) {
            params = params.append('query', filtro);
        }

        params = sapedUtil.setPageableParams(pageable, params);
        return this.http.get(this.searchUrl, {params: params});

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