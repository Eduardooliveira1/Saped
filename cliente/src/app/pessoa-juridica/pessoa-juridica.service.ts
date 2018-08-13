import { Observable } from 'rxjs/Observable';
import { PessoaJuridicaCadastro } from './pessoa-juridica-cadastro.model';
import { HttpService } from '@basis/angular-components';
import { environment } from '../../environments/environment.prod';
import { Injectable } from "@angular/core";
import { Pageable } from '../util/pageable-request';
import { RequestOptions, Response } from '@angular/http';

@Injectable()
export class PessoaJuridicaService {

    resourceUrl = environment.apiUrl + "/pessoa-juridica";
    searchUrl = environment.apiUrl + "/pessoas-juridicas";

    constructor(private http: HttpService) { }

    listarDirigentes(filtro: string, pageable: Pageable, callback?: any) {
        const options = new RequestOptions({ params: pageable });
        if (filtro) {
            options.params.append("query", filtro);
        }
        return this.http.get(this.searchUrl, options);
    }

    cadastrar(pessoaJuridica: PessoaJuridicaCadastro): Observable<PessoaJuridicaCadastro> {
        const copy = this.convert(pessoaJuridica);
        return this.http.post(this.resourceUrl, copy).map((res : Response) => {
            return res.json();
        });
    }

    atualizar(pessoaJuridica: PessoaJuridicaCadastro): Observable<PessoaJuridicaCadastro> {
        const copy = this.convert(pessoaJuridica);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    obter(id: number): Observable<PessoaJuridicaCadastro> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    obterRepresentantes(idPj: number) {
        return this.http.get(`${this.resourceUrl}/${'representantes'}/${idPj}`).map((res: Response) => {
            return res.json();
        });
    }
    remover(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convert(pessoaJuridica: PessoaJuridicaCadastro): PessoaJuridicaCadastro {
        const copy: PessoaJuridicaCadastro = Object.assign({}, pessoaJuridica);
        return copy;
    }

    listarTodas() {
        return this.http.get(`${this.searchUrl}/${'todas'}`).map((res: Response) => {
            return res.json();
        });
    }

}