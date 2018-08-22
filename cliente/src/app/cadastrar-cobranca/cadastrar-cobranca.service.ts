import { HttpService } from '@basis/angular-components';
import { environment } from '../../environments/environment.prod';
import { Injectable } from "@angular/core";
import { RequestOptions, Response } from '@angular/http';

@Injectable()
export class CadastarCobrancaService {

    searchUrl = environment.apiUrl + "/listagem-cobranca";

    constructor(private http: HttpService) { }

    obterQuintosDiasUtis(ano: string) {
        return this.http.get(`${this.searchUrl}/${ano}`).map((res: Response) => {
            return res.json();
        });
    }

    obterCobrancasDoAno(ano: string, idPessoaJuridica: string) {
        return this.http.get(`${this.searchUrl}/${ano}/${idPessoaJuridica}`).map((res: Response) => {
            return res.json();
        });
    }

}