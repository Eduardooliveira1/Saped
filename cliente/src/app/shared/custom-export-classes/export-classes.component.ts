import {Component} from '@angular/core';
import {PtBr} from './calendar';
import {CurrentYearList} from './dropdown-mes-referencia';

@Component({
  selector: 'app-export-classes',
  template: 'exporters'
})
export class ExportClassesComponent {
  calendar: PtBr;
  dropDownMesReferencia: CurrentYearList;
  constructor() {
    this.calendar = new PtBr();
    this.dropDownMesReferencia = new CurrentYearList();
  }
}
