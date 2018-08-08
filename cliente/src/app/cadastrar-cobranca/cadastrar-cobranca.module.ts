import { InputTextModule } from 'primeng/primeng';
import { CustomComponentsModule } from './../shared/custom-components/custom-components.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CadastrarCobrancaComponent } from './cadastrar-cobranca/cadastrar-cobranca.component';
import { DropdownModule } from 'primeng/primeng';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

@NgModule({
  imports: [
    CommonModule,
    DropdownModule,
    FontAwesomeModule,
    CustomComponentsModule,
    InputTextModule,
  ],
  declarations: [CadastrarCobrancaComponent]
})
export class CadastrarCobrancaModule { }