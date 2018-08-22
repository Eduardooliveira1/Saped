import { PessoaRepresentanteService } from './pessoa-representante.service';
import { EnumService } from '../shared/enum.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { pessoaJuridicaRoute } from './pessoa-juridica.route';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaJuridicaListComponent } from './pessoa-juridica-list/pessoa-juridica-list.component';
import { RouterModule } from '@angular/router';
import { ButtonModule, DataTableModule, InputMaskModule, DropdownModule, BlockUIModule, ConfirmDialogModule, ConfirmationService, DialogModule, CheckboxModule } from 'primeng/primeng';
import { PessoaJuridicaService } from './pessoa-juridica.service';
import {Ng2BRPipesModule} from 'ng2-brpipes';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { PessoaJuridicaFormComponent } from './pessoa-juridica-form/pessoa-juridica-form.component';
import { CustomComponentsModule } from '../shared/custom-components/custom-components.module';
import { PessoaRepresentanteListComponentComponent } from './pessoa-representante-list-component/pessoa-representante-list-component.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(pessoaJuridicaRoute),
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
    ConfirmDialogModule,
    DialogModule,
    InputMaskModule,
    CheckboxModule

  ],
  declarations: [PessoaJuridicaListComponent, PessoaJuridicaFormComponent, PessoaRepresentanteListComponentComponent],
  providers:[
      PessoaJuridicaService, EnumService, ConfirmationService, PessoaRepresentanteService
  ],
  exports: [Ng2BRPipesModule]
})
export class PessoaJuridicaModule { }
