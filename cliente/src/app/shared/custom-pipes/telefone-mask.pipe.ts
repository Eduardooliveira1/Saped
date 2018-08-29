import {Pipe, PipeTransform} from '@angular/core';


@Pipe({ name: 'telefoneMaskPipe' })
export class TelefoneMaskPipe implements PipeTransform {

  private TRACO: string;
  private THOUSANDS_SEPARATOR: string;

  constructor() {
    // TODO comes from configuration settings
    this.TRACO = '-';
  }

  static parse (value: string): string {
    return value.replace('-', '');
  }

  transform(value: string): string {
    const numbers = value.match(/\d/g);
    let numberLength = 0;
    if (numbers) {
      numberLength = numbers.join('').length;
    }

    if (numberLength > 8) {
      // return [/\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
      return '';
    } else {
      // return [/\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
      return '';
    }
  }


}
