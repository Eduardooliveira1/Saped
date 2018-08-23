import { Cobranca } from './cobranca-model';
import { HttpService } from '@basis/angular-components';
import { environment } from '../../environments/environment.prod';
import { Injectable } from "@angular/core";
import { Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CadastarCobrancaService {

    searchUrl = environment.apiUrl + "/listagem-cobranca";
    resourceUrl = "/boleto";
    
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

    gerarBoleto(cobranca: Cobranca): Observable<Cobranca> {

        return this.http.post(this.resourceUrl+"/gerar-boleto", cobranca).map((res : Response) => {
            return res.json();
        });
    }
    
}