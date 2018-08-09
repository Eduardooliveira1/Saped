import { MensagensUtils } from './../util/mensagens-util';
import { EnumService } from './../shared/enum.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { comunicacaoRoute } from './comunicacao.route';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ComunicacaoListComponent } from './comunicacao-list/comunicacao-list.component';
import { RouterModule } from '@angular/router';
import { ButtonModule, DataTableModule, InputMaskModule, DropdownModule, BlockUIModule, ConfirmDialogModule, ConfirmationService } from 'primeng/primeng';
import { ComunicacaoService } from './comunicacao.service';
import {Ng2BRPipesModule} from 'ng2-brpipes';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CustomComponentsModule } from '../shared/custom-components/custom-components.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(comunicacaoRoute),
    FormsModule,
    ButtonModule,
    DataTableModule,
    Ng2BRPipesModule,
    FontAwesomeModule,
    CustomComponentsModule,
    InputMaskModule,
    ReactiveFormsModule,
    DropdownModule,
    BlockUIModule,
    ConfirmDialogModule
  ],
  declarations: [ComunicacaoListComponent],
  providers:[
      ComunicacaoService, EnumService, ConfirmationService
  ],
  exports: [Ng2BRPipesModule]
})
export class ComunicacaoModule { }
