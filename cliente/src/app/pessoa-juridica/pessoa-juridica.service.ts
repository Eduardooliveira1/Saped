import { Observable } from 'rxjs/Observable';
import { PessoaJuridicaCadastro } from './pessoa-juridica-cadastro.model';
import { HttpService } from '@basis/angular-components';
import { environment } from './../../environments/environment.prod';
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
        return this.http.post(this.resourceUrl, copy).map((res) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    atualizar(pessoaJuridica: PessoaJuridicaCadastro): Observable<PessoaJuridicaCadastro> {
        const copy = this.convert(pessoaJuridica);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    obter(id: number): Observable<PessoaJuridicaCadastro> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    private convert(pessoaJuridica: PessoaJuridicaCadastro): PessoaJuridicaCadastro {
        const copy: PessoaJuridicaCadastro = Object.assign({}, pessoaJuridica);
        return copy;
    }

    private convertItemFromServer(json: any): PessoaJuridicaCadastro {
        const entity: PessoaJuridicaCadastro = Object.assign(new PessoaJuridicaCadastro(), json);
        return entity;
    }

}