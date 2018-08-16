import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {Ng2BRPipesModule} from 'ng2-brpipes';
import {CurrencyMaskModule} from 'ng2-currency-mask';
import {
  BlockUIModule,
  ButtonModule,
  ConfirmationService,
  ConfirmDialogModule,
  DataTableModule,
  DropdownModule,
  InputMaskModule,
  MultiSelectModule
} from 'primeng/primeng';
import {PessoaJuridicaService} from '../pessoa-juridica/pessoa-juridica.service';
import {CustomComponentsModule} from '../shared/custom-components/custom-components.module';
import {EnumService} from '../shared/enum.service';
import {RelatorioPagamentoListComponent} from './pagamentos/pagamentos-list-component/relatorio-pagamento-list.component';
import {RelatorioPagamentoComponent} from './pagamentos/relatorio-pagamento.component';
import {relatoriosRoutes} from './relatorios.route';
import {RelatoriosService} from './relatorios.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(relatoriosRoutes),
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
    MultiSelectModule,
    CurrencyMaskModule
  ],
  declarations: [RelatorioPagamentoComponent , RelatorioPagamentoListComponent],
  providers: [
    RelatoriosService, EnumService, ConfirmationService, PessoaJuridicaService
  ],
  exports: [Ng2BRPipesModule]
})
export class RelatoriosModule { }
