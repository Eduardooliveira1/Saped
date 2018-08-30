import {Injectable} from '@angular/core';

import {Response} from '@angular/http';
import {HttpService} from '@basis/angular-components';
import {environment} from '../../environments/environment.prod';
import {PessoaRepresentanteEmailECnpj} from './pessoa-representante-email-e-cnpj';

@Injectable()
export class PessoaRepresentanteService {

  resourceUrl = environment.apiUrl + '/pessoas-representantes';

  constructor(private http: HttpService) {
  }

  obterRepresentantes(idPj: number) {
    return this.http.get(`${this.resourceUrl}/${idPj}`).map((res: Response) => {
      return res.json();
    });
  }

  isEmailECnpjValid(cnpjEEmail: PessoaRepresentanteEmailECnpj) {
    let result: boolean;
    this.http.post(this.resourceUrl, cnpjEEmail).map((res: Response) => {
        res.json().subscribe((r: boolean) => {
            result = r;
          }
        );
      }, (err: any) => {
      console.error(err);
    }
    );
    if (!result) {
      result = false;
    }
    return result;
  }

}
