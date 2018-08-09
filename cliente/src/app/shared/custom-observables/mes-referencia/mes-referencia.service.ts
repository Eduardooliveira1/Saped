import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {MesReferencia} from './mes-referencia-';

@Injectable()
export class ItemService {

  public $visible = new Subject<MesReferencia>();
  constructor() { }

}
