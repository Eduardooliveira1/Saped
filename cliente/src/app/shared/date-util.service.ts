import {DatePipe} from '@angular/common';
import {Injectable} from '@angular/core';
import {HttpService} from '@basis/angular-components';
import {Observable} from '../../../node_modules/rxjs';
import {environment} from '../../environments/environment.prod';


/**
 * An utility service for date.
 */
@Injectable()
export class JhiDateUtils {

  private pattern = 'yyyy-MM-dd';

  private datePipe: DatePipe;

  constructor(private http: HttpService) {
    this.datePipe = new DatePipe('pt-BR');
  }

  resourceUrl = environment.apiUrl + '/date-util';

  /**
   * Method to convert the date time from server into JS date object
   */
  convertDateTimeFromServer(date: any) {
    if (date) {
      return new Date(date);
    } else {
      return null;
    }
  }

  /**
   * Method to convert the date from server into JS date object
   */
  convertLocalDateFromServer(date: any) {
    if (date) {
      const dateString = date.split('-');
      return new Date(dateString[0], dateString[1] - 1, dateString[2]);
    }
    return null;
  }

  /**
   * Method to convert the JS date object into specified date pattern
   */
  convertLocalDateToServer(date: any, pattern = this.pattern) {
    if (date) {
      const newDate = new Date(date);
      return this.datePipe.transform(newDate, pattern);
    } else {
      return null;
    }
  }

  /**
   * Method to get the default date pattern
   */
  dateformat() {
    return this.pattern;
  }

  listarMesReferencia() {
    // return this.http.get(this.resourceUrl + '/mes-referencia').map((res: Response) => {
    //   return res.json();
    // });
    return new Observable((observer) => {
      const year = new Date().getFullYear();
      observer.next([{'id': 1, 'descricao': 'Janeiro/' + year},
        {'id': 2, 'descricao': 'Fevereiro/' + year},
        {'id': 3, 'descricao': 'Março/' + year},
        {'id': 4, 'descricao': 'Abril/' + year},
        {'id': 5, 'descricao': 'Maio/' + year},
        {'id': 6, 'descricao': 'Junho/' + year},
        {'id': 7, 'descricao': 'Julho/' + year},
        {'id': 8, 'descricao': 'Agosto/' + year},
        {'id': 9, 'descricao': 'Setembro/' + year},
        {'id': 10, 'descricao': 'Outubro/' + year},
        {'id': 11, 'descricao': 'Novembro/' + year},
        {'id': 12, 'descricao': 'Dezembro/' + year}]);
    });
  }

  // TODO Change this method when moving from datetime-local input to NgbDatePicker
  toDate(date: any): Date {
    if (date === undefined || date === null) {
      return null;
    }
    const dateParts = date.split(/\D+/);
    if (dateParts.length === 7) {
      return new Date(dateParts[0], dateParts[1] - 1, dateParts[2], dateParts[3], dateParts[4], dateParts[5], dateParts[6]);
    }
    if (dateParts.length === 6) {
      return new Date(dateParts[0], dateParts[1] - 1, dateParts[2], dateParts[3], dateParts[4], dateParts[5]);
    }
    return new Date(dateParts[0], dateParts[1] - 1, dateParts[2], dateParts[3], dateParts[4]);
  }
}
