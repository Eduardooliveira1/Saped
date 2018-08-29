import {Directive, ElementRef, HostListener, Renderer} from '@angular/core';

@Directive({
  selector: '[telefoneMask]'
})
export class TelefoneMaskDirective {
  public nativeElement: HTMLInputElement;

  constructor(public element: ElementRef, public render: Renderer) {
    this.nativeElement = this.element.nativeElement;
    this.nativeElement.maxLength = 10;
  }

  @HostListener('keydown', ['$event']) onKeyDown(event) {
    const e = <KeyboardEvent>event;
    // Permitir deletar da string:
    if ([46, 8, 9, 27, 13].indexOf(e.keyCode) !== -1) {
      return;
    }
    const ch = (e.key);
    const regEx = new RegExp('^[0-9]*$');
    if (regEx.test(ch)) {
      return;
    } else {
      e.preventDefault();
    }
  }

  @HostListener('keyup', ['$event']) onKeyUp(e: Event) {
    let v = this.nativeElement.value;
    v = v.replace(/\D/g, '');
    v = v.replace(/^(\d)/g, '$1');
    v = v.replace(/(\d)(\d{4})$/, '$1-$2');
    this.nativeElement.value = v;
  }

}
