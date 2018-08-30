import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'stringToSlash' })
export class StringToSlashPipe implements PipeTransform {
  transform(value: string) {
      return '-';
  }
}
