import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { environment } from '../../environments/environment.prod';

@Injectable()
export class EnumService{

    resourceUrl = environment.apiUrl+"/enumerations/";
    constructor(private http: HttpClient) {}

    public static SERVICO_TIPO_ENDERECO = 'tipos-endereco';
    
    listarEnum(nomeEnum: String, callback?: any){
        return this.http.get(this.resourceUrl+nomeEnum).map(res=>{
            return res;
        })
    }
}