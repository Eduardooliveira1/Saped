import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment.prod';
import { Injectable } from "@angular/core";

import { Response } from '@angular/http';

@Injectable()
export class PessoaRepresentanteService {

    resourceUrl = environment.apiUrl + "/pessoas-representantes";

    constructor(private http: HttpClient) { }

    obterRepresentantes(idPj: number) {
        return this.http.get(`${this.resourceUrl}/${idPj}`).map((res: Response) => {
            return res;
        });
    }

}