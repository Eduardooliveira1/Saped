import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { PessoaJuridicaListComponent } from './pessoa-juridica-list/pessoa-juridica-list.component'

export const pessoaJuridicaRoute: Routes = [
    { path: '', component: PessoaJuridicaListComponent }
];

