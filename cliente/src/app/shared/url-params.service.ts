// import {HttpClient, HttpHeaders} from '@angular/common/http';
// import {Inject, Injectable, InjectionToken} from '@angular/core';
// import {Observable} from '../../../node_modules/rxjs';
// import {UrlParams} from './url-params';
//
// export const url = new InjectionToken('url');
// export const params = new InjectionToken('params');
//
// @Injectable()
// export class UrlParamsService {
//
//   results: UrlParams[];
//
//   constructor(@Inject(url) public url_: string,
//               private httpClient: HttpClient,
//               @Inject(params) public params_: URLSearchParams) {
//     this.results = [];
//   }
//
//   private headers = new HttpHeaders().append('Content-Type', 'application/json');
//
//   search(term: string): Observable<any> {
//     const params_ = this.params_;
//     const searchUrl = this.url_ + '/';
//     const parametrizedURl = `${searchUrl}?term=${term}`;
//
//     // const searchUrl = this.url_ + '/';
//     //   const parametrizedURl = `${searchUrl}?term=${term}`;
//     //   return this.httpClient.request(parametrizedURl).map( res => {
//     //     return res.json().results.map(item => {
//     //         return new UrlParams(item.cnpj);
//     //       });
//     // });
//   }
//
// }
