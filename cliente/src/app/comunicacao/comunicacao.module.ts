import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { comunicacaoRoute } from './comunicacao.route';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunicacaoListComponent } from './comunicacao-list/comunicacao-list.component';
import { RouterModule } from '@angular/router';
import { ButtonModule, DataTableModule, BlockUIModule} from 'primeng/primeng';
import { ComunicacaoService } from './comunicacao.service';
import { CustomComponentsModule } from '../shared/custom-components/custom-components.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(comunicacaoRoute),
    FormsModule,
    ButtonModule,
    DataTableModule,
    CustomComponentsModule,
    ReactiveFormsModule,
    BlockUIModule,
  ],
  declarations: [ComunicacaoListComponent],
  providers:[
      ComunicacaoService
  ],
  exports: []
})
export class ComunicacaoModule { }
