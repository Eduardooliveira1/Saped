import { Router } from '@angular/router';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

    constructor( private router: Router) {

    }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const modified = req.clone({setHeaders: {"Authorization": this._buildAuthHeader()}});
        return next.handle(modified).do((event: HttpEvent<any>) => {
            if (event instanceof HttpResponse) {
              // tratar alguma resposta genérica
            }
          }, (err: any) => {
            if (err instanceof HttpErrorResponse) {
              if (err.status === 401) {
                    this.router.navigateByUrl('login');
              }
            }
          });
    }

    private _buildAuthHeader(): string {
        return "Bearer " + localStorage.getItem("id_token");
    }
}




