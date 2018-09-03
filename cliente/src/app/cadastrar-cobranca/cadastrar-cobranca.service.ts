import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Response } from '@angular/http';
import { environment } from '../../environments/environment.prod';

@Injectable()
export class CadastarCobrancaService {

    searchUrl = environment.apiUrl + "/quinto-dia-util";

    constructor(private http: HttpClient) { }

    obterQuintosDiasUtis(ano: string) {
        return this.http.get(`${this.searchUrl}/${ano}`).map((res: Response) => {
            return res;
        });
    }
}