import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DropdownModule, InputTextareaModule, InputTextModule} from "primeng/primeng";
import {CustomInputTextComponent} from "./custom-input-text/custom-input-text.component";
import {CustomTextareaComponent} from './custom-textarea/custom-textarea.component';

@NgModule({
    imports: [
        CommonModule,
        InputTextModule,
        InputTextareaModule,
        DropdownModule
    ],
    declarations: [CustomInputTextComponent, CustomTextareaComponent],
    exports:[CustomInputTextComponent, CustomTextareaComponent]
})
export class CustomComponentsModule {
}
