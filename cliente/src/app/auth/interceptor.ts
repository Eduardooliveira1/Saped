import { sapedUtil } from '../shared/metodos/sapedUtil';
import { Router } from '@angular/router';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor( private router: Router) { }
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const modified = req.clone(sapedUtil.setHeaders());

      return next.handle(modified).do((event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
            // tratar alguma resposta genérica
          }
        }, (err: any) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === 401) {
                sapedUtil.navegarParaLogin(this.router);
            }
          }
        });
  }
}




