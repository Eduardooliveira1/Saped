import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {PageNotificationService} from '@basis/angular-components';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {Ng2BRPipesModule} from 'ng2-brpipes';
import {CurrencyMaskModule} from 'ng2-currency-mask';

import {
  BlockUIModule,
  ButtonModule,
  CalendarModule,
  ConfirmationService,
  ConfirmDialogModule,
  DataTableModule,
  DropdownModule,
  InputMaskModule,
  MultiSelectModule
} from 'primeng/primeng';
import { CURRENCY_MASK_CONFIG } from 'ng2-currency-mask/src/currency-mask.config';
import { PessoaJuridicaService } from '../pessoa-juridica/pessoa-juridica.service';
import { DotToCommaPipe } from '../shared';
import { CustomComponentsModule } from '../shared/custom-components/custom-components.module';
import { CustomCurrencyMaskConfig } from '../shared/custom-mask-configs/custom-currency-mask-config';
import { BoletoDataVencimentoPipe } from '../shared/custom-pipes/boleto-data-vencimento.pipe';
import { StatusBoletoIdToDescricaoPipe } from '../shared/custom-pipes/status-boleto-id-to-descricao.pipe';
import { EnumService } from '../shared/enum.service';
import { RelatorioPagamentoComponent } from './pagamentos/relatorio-pagamento.component';
import { relatoriosRoutes } from './relatorios.route';
import { RelatoriosService } from './relatorios.service';


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
    CurrencyMaskModule,
    CalendarModule],
  declarations: [RelatorioPagamentoComponent, StatusBoletoIdToDescricaoPipe, DotToCommaPipe, BoletoDataVencimentoPipe],
  providers: [{provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig},
    RelatoriosService, EnumService, ConfirmationService, PessoaJuridicaService, PageNotificationService
  ],
  exports: [Ng2BRPipesModule]
})
export class RelatoriosModule {}
