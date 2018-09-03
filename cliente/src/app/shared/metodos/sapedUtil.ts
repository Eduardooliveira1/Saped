import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Headers, RequestOptions } from '@angular/http';


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

  public static insereAutorizacaoHeader(options: RequestOptions) {
    options.headers =  new Headers();
    options.headers.append('Authorization', this.buildAuthHeader());
  }

  public static navegarParaLogin(router: Router) {
    router.navigateByUrl('login');
  }
}
