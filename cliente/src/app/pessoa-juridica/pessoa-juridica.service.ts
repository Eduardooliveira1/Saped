import {HttpClient, HttpParams} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../environments/environment.prod';
import {Pageable} from '../util/pageable-request';
import {sapedUtil} from './../shared/metodos/sapedUtil';
import {PessoaJuridicaCadastro} from './pessoa-juridica-cadastro.model';

@Injectable()
export class PessoaJuridicaService {

    resourceUrl = environment.apiUrl + "/pessoa-juridica";
    searchUrl = environment.apiUrl + "/pessoas-juridicas";

    constructor(private http: HttpClient) { }

    listarDirigentes(filtro: string, pageable: Pageable, callback?: any): any {
        let params = new HttpParams();
        if (filtro) {
            params = params.append('query', filtro);
        }

        params = sapedUtil.setPageableParams(pageable, params);
        return this.http.get(this.searchUrl, {params: params});
    }

    cadastrar(pessoaJuridica: PessoaJuridicaCadastro): Observable<PessoaJuridicaCadastro> {
        const copy = this.convert(pessoaJuridica);

        return this.http.post(this.resourceUrl, copy).map((res : PessoaJuridicaCadastro) => {
            return res;
        });
    }

    atualizar(pessoaJuridica: PessoaJuridicaCadastro): Observable<PessoaJuridicaCadastro> {
        const copy = this.convert(pessoaJuridica);
        return this.http.put(this.resourceUrl, copy).map((res: PessoaJuridicaCadastro) => {
            return res;
        });
    }

    obter(id: number): Observable<PessoaJuridicaCadastro> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: PessoaJuridicaCadastro) => {
            return res;
        });
    }

    remover(id: number): Observable<any> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convert(pessoaJuridica: PessoaJuridicaCadastro): PessoaJuridicaCadastro {
        return Object.assign({}, pessoaJuridica);
    }

    listarTodas() {
        return this.http.get(`${this.searchUrl}/${'todas'}`).map((res: any) => {
            return res;
        });
    }

    listarNomes() {
      return this.http.get(this.resourceUrl + '/nomes').map((res: Response) => {
        return res;
      });
    }
};  