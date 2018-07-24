import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { PessoaJuridicaListComponent } from './pessoa-juridica-list/pessoa-juridica-list.component'
import { PessoaJuridicaFormComponent } from './pessoa-juridica-form/pessoa-juridica-form.component';

export const pessoaJuridicaRoute: Routes = [
    { path: '', component: PessoaJuridicaListComponent },
    { path: 'cadastro', component: PessoaJuridicaFormComponent },
    { path: 'editar/:id', component: PessoaJuridicaFormComponent }
];

