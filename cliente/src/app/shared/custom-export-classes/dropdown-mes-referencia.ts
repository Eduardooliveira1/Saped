import {Injectable} from '@angular/core';

@Injectable()
export class CurrentYearList  {
  year = new Date().getFullYear();
  public items: any[] = [{'id': 1, 'descricao': 'Janeiro/' + this.year},
    {'id': 2, 'descricao': 'Fevereiro/' +  this.year},
    {'id': 3, 'descricao': 'Março/' +  this.year},
    {'id': 4, 'descricao': 'Abril/' +  this.year},
    {'id': 5, 'descricao': 'Maio/' +  this.year},
    {'id': 6, 'descricao': 'Junho/' +  this.year},
    {'id': 7, 'descricao': 'Julho/' +  this.year},
    {'id': 8, 'descricao': 'Agosto/' +  this.year},
    {'id': 9, 'descricao': 'Setembro/' +  this.year},
    {'id': 10, 'descricao': 'Outubro/' +  this.year},
    {'id': 12, 'descricao': 'Dezembro/' +  this.year}];

  constructor() {
  }

}

