import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {Ng2BRPipesModule} from 'ng2-brpipes';
import {
  BlockUIModule,
  ButtonModule,
  CheckboxModule,
  ConfirmationService,
  ConfirmDialogModule,
  DataTableModule,
  DialogModule,
  DropdownModule,
  InputMaskModule
} from 'primeng/primeng';
import {CustomComponentsModule} from '../shared/custom-components/custom-components.module';
import {CustomDirectivesModule} from '../shared/custom-directives/custom-directives.module';
import {EnumService} from '../shared/enum.service';
import {PessoaJuridicaFormComponent} from './pessoa-juridica-form/pessoa-juridica-form.component';
import {PessoaJuridicaListComponent} from './pessoa-juridica-list/pessoa-juridica-list.component';
import {pessoaJuridicaRoute} from './pessoa-juridica.route';
import {PessoaJuridicaService} from './pessoa-juridica.service';
import {PessoaRepresentanteListComponentComponent} from './pessoa-representante-list-component/pessoa-representante-list-component.component';
import {PessoaRepresentanteService} from './pessoa-representante.service';

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
    CheckboxModule,
    CustomDirectivesModule
  ],
  declarations: [PessoaJuridicaListComponent, PessoaJuridicaFormComponent, PessoaRepresentanteListComponentComponent],
  providers: [
      PessoaJuridicaService, EnumService, ConfirmationService, PessoaRepresentanteService
  ],
  exports: [Ng2BRPipesModule]
})
export class PessoaJuridicaModule { }
