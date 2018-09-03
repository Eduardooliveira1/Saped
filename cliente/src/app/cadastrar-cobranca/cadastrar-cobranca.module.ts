import { RouterModule } from '@angular/router';


import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ButtonModule, CheckboxModule, DataTableModule, DialogModule, DropdownModule, InputTextModule } from 'primeng/primeng';
import { CustomComponentsModule } from '../shared/custom-components/custom-components.module';
import { cadastrarCobrancaRoute } from './cadastrar-cobranca.route';
import { CadastarCobrancaService } from './cadastrar-cobranca.service';
import { CadastrarCobrancaComponent } from './cadastrar-cobranca/cadastrar-cobranca.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaJuridicaService } from '../pessoa-juridica/pessoa-juridica.service';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    DropdownModule,
    ButtonModule,
    FontAwesomeModule,
    CustomComponentsModule,
    InputTextModule,
    DataTableModule,
    FormsModule,
    CheckboxModule,
    RouterModule.forChild(cadastrarCobrancaRoute),
    DialogModule

  ],
  declarations: [CadastrarCobrancaComponent],
  providers:[
    PessoaJuridicaService, CadastarCobrancaService
],
})
export class CadastrarCobrancaModule { }