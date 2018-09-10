import {Pipe, PipeTransform} from '@angular/core';

@Pipe({name: 'dotToComma' })
export class DotToCommaPipe implements PipeTransform {
  transform(value: string) {
    return value.replace(/,/g, '.' ).replace(/.([^.]+)$/g, ',$1');
  }
}
