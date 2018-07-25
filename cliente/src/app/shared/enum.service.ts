import { Observable } from 'rxjs/Observable';
import { HttpService } from '@basis/angular-components';
import { environment } from './../../environments/environment.prod';
import { Injectable } from "@angular/core";
import { RequestOptions } from '@angular/http';

@Injectable()
export class EnumService{

    resourceUrl = environment.apiUrl+"/enumerations/";
    constructor(private http: HttpService) {}

    public static SERVICO_TIPO_ENDERECO = 'tipos-endereco';
    
    listarEnum(nomeEnum: String, callback?: any){
        return this.http.get(this.resourceUrl+nomeEnum).map(res=>{
            return res.json();
        })
    }
}