import {Routes} from '@angular/router';
import {RelatorioPagamentoListComponent} from './pagamentos/pagamentos-list-component/relatorio-pagamento-list.component';

import {RelatorioPagamentoComponent} from './pagamentos/relatorio-pagamento.component';

export const relatoriosRoutes: Routes = [
    { path: 'pagamentos', component: RelatorioPagamentoComponent },
    { path: 'pagamentos/pagamentos-list-component', component: RelatorioPagamentoListComponent}
];

