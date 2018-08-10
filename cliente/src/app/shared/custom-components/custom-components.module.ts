import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {DropdownModule, InputTextareaModule, InputTextModule} from 'primeng/primeng';
import {MesReferencia} from '../mes-referencia';
import {CustomInputTextComponent} from './custom-input-text/custom-input-text.component';
import {CustomTextareaComponent} from './custom-textarea/custom-textarea.component';

@NgModule({
    imports: [
        CommonModule,
        InputTextModule,
        InputTextareaModule,
        DropdownModule
    ],
    declarations: [CustomInputTextComponent, CustomTextareaComponent, MesReferencia],
    exports:[CustomInputTextComponent, CustomTextareaComponent, MesReferencia]
})
export class CustomComponentsModule {
}
