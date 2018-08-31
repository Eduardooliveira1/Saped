import {Injectable} from '@angular/core';

import {Response} from '@angular/http';
import {HttpService} from '@basis/angular-components';
import {environment} from '../../environments/environment.prod';
import {CustomUtils} from '../util/custom-utils';
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

  validaEmailECnpj(cnpjEEmail: PessoaRepresentanteEmailECnpj) {
    let result: boolean;
    result = false;
    const copy = CustomUtils.convert(cnpjEEmail);
    return this.http.post(this.resourceUrl + '/validar-esqueci-a-senha', cnpjEEmail).map((res: Response) => {
        return res.json();
      }
    );
  }

}
