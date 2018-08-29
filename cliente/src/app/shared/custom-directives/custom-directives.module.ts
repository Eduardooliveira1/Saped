import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {TelefoneMaskDirective} from './telefone-mask.directive';

@NgModule({
  providers: [],
  declarations: [TelefoneMaskDirective],
  exports: [TelefoneMaskDirective],
  imports: [
    FormsModule,
    CommonModule
  ]
})
export class CustomDirectivesModule {

}
