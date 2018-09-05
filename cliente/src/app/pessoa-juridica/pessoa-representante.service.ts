import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

import {Response} from '@angular/http';
import {environment} from '../../environments/environment.prod';
import {CustomUtils} from '../util/custom-utils';
import {PessoaRepresentanteEmailECnpj} from './pessoa-representante-email-e-cnpj';

@Injectable()
export class PessoaRepresentanteService {

  resourceUrl = environment.apiUrl + '/pessoas-representantes';

  constructor(private http: HttpClient) {
  }

  obterRepresentantes(idPj: number) {
    return this.http.get(`${this.resourceUrl}/${idPj}`).map((res: Response) => {
      return res;
    });
  }

  validaEmailECnpj(cnpjEEmail: PessoaRepresentanteEmailECnpj) {
    const copy = CustomUtils.convert(cnpjEEmail);
    return this.http.post(this.resourceUrl + '/validar-esqueci-a-senha', copy).map((res: Response) => {
        return res;
      }
    );
  }

}
