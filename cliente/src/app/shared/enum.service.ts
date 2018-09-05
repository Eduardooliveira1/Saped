import { HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { environment } from '../../environments/environment.prod';

@Injectable()
export class EnumService{

    resourceUrl = environment.apiUrl+"/enumerations/";
    constructor(private http: HttpClient) {}

    public static SERVICO_TIPO_ENDERECO = 'tipos-endereco';
    public static SERVICO_LIST_NOMES_PJ = 'nomes-pessoas-juridicas';
    public static STATUS_BOLETO = 'status-boleto';

    listarEnum(nomeEnum: String, callback?: any){
        return this.http.get(this.resourceUrl+nomeEnum).map(res=>{
            return res;
        })
    }
}