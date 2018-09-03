import { HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';


 export class sapedUtil{
  constructor() { }
 public static obterToken() {
     return localStorage.getItem("id_token");
 }

 public static setHeaders() {
    return { setHeaders: {"Authorization": this.buildAuthHeader()} } 
  }
  
  public static buildAuthHeader(): string {
      return "Bearer " + this.obterToken();
  }

  public static navegarParaLogin(router: Router) {
    router.navigateByUrl('login');
  }

  public static setPageableParams(argument, params: HttpParams) : HttpParams {
    for (let prop of Object.getOwnPropertyNames(argument)) {
      params = params.append(prop, argument[prop]);
    }
    return params;
  }

}