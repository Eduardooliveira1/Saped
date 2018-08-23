import { AcaoCobranca } from '../shared/custom-pipes/acao-cobranca.pipe';
import { CadastarCobrancaService } from './cadastrar-cobranca.service';
import { cadastrarCobrancaRoute } from './cadastrar-cobranca.route';
import { RouterModule } from '@angular/router';
import { InputTextModule, DataTableModule, ButtonModule, DialogModule, CheckboxModule } from 'primeng/primeng';
import { CustomComponentsModule } from '../shared/custom-components/custom-components.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CadastrarCobrancaComponent } from './cadastrar-cobranca/cadastrar-cobranca.component';
import { DropdownModule } from 'primeng/primeng';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaJuridicaService } from '../pessoa-juridica/pessoa-juridica.service';
import { FormsModule } from '@angular/forms';
import { MesReferencia } from '../shared/custom-pipes/mes-referencia.pipe.1';

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
  declarations: [CadastrarCobrancaComponent, AcaoCobranca,MesReferencia],
  providers:[
    PessoaJuridicaService, CadastarCobrancaService
],
})
export class CadastrarCobrancaModule { }