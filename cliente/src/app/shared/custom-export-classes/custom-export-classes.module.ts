import {NgModule} from '@angular/core';
import {CalendarPtBr} from './calendar-pt-br';
import {CustomInputTextComponent} from './custom-input-text/custom-input-text.component';
import {CustomTextareaComponent} from './custom-textarea/custom-textarea.component';

@NgModule({
  declarations: [CalendarPtBr],
  exports: [CalendarPtBr]
})
export class CustomExportClassesModule {}
