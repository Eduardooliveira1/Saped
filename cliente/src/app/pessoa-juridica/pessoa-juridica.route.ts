import {Routes} from '@angular/router';

import {PessoaJuridicaListComponent} from './pessoa-juridica-list/pessoa-juridica-list.component';

export const pessoaJuridicaRoute: Routes = [
    { path: '', component: PessoaJuridicaListComponent },
    { path: '/excluir/:id', component: PessoaJuridicaListComponent }
];

