import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Cobranca, FiltroListagemCobranca, DadosGerarBoleto } from './cobranca-model';
import { Injectable } from "@angular/core";
import { Response } from '@angular/http';
import { environment } from '../../environments/environment.prod';

@Injectable()
export class CadastarCobrancaService {

    searchUrl = environment.apiUrl + "/listagem-cobranca";
    resourceUrl = environment.apiUrl + "/gerar-boleto";
    
    constructor(private http: HttpClient) { }

    obterQuintosDiasUtis(ano: string) {
        return this.http.get(`${this.searchUrl}/${ano}`).map((res: Response) => {
            return res;
        });
    }

    obterCobrancasDoAno(filtroListagemCobranca: FiltroListagemCobranca) : any{
        return this.http.post(this.searchUrl, filtroListagemCobranca).map((res: Response) => {
            debugger;
            return res;
        });
    }

    gerarBoleto(dadosDoBoleto: DadosGerarBoleto): Observable<Cobranca> {
        return this.http.post(this.resourceUrl, dadosDoBoleto).map((res : Cobranca) => {
            return res;
        });
    }
    
}