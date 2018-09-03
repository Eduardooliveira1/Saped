import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment.prod';
import { UsuarioLogin } from './usuario-login-model';

@Injectable()
export class LoginService {

    resourceUrl = environment.apiUrl + "/authenticate";

    constructor(private http: HttpClient) { }

    efetuarLogin(usuarioLogin: UsuarioLogin): Observable<UsuarioLogin> {
        return this.http.post(this.resourceUrl, usuarioLogin).map((res : any) => {
            return res;
        });
    }
}