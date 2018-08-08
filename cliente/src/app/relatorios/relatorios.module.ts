import { EnumService } from '../shared/enum.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {
    ButtonModule, DataTableModule, InputMaskModule, DropdownModule, BlockUIModule, ConfirmDialogModule,
    ConfirmationService, MultiSelectModule
} from 'primeng/primeng';
import {Ng2BRPipesModule} from 'ng2-brpipes';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CustomComponentsModule } from '../shared/custom-components/custom-components.module';
import {relatoriosRoutes} from './relatorios.route';
import {RelatorioPagamentoListComponent} from './pagamentos/relatorio-pagamento-list.component';
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
        MultiSelectModule
    ],
    declarations: [RelatorioPagamentoListComponent],
    providers: [
        RelatoriosService, EnumService, ConfirmationService
    ],
    exports: [Ng2BRPipesModule]
})
export class RelatoriosModule { }
