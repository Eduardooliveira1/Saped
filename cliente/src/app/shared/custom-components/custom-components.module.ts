import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {CurrencyMaskModule} from 'ng2-currency-mask';
import {DropdownModule, InputTextareaModule, InputTextModule} from 'primeng/primeng';
import {CustomInputTextComponent} from './custom-input-text/custom-input-text.component';
import {CustomTextareaComponent} from './custom-textarea/custom-textarea.component';

@NgModule({
  imports: [
    CommonModule,
    InputTextModule,
    InputTextareaModule,
    DropdownModule,
    CurrencyMaskModule,
    FormsModule
  ],
  declarations: [CustomInputTextComponent, CustomTextareaComponent],
  exports: [CustomInputTextComponent, CustomTextareaComponent]
})
export class CustomComponentsModule {
}
