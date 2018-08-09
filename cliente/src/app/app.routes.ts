import { LoginLayoutComponent } from './layouts/login-layout/login-layout.component';
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { LoginComponent } from './login/login.component';
import { LoginSuccessComponent } from './login-success/login-success.component';
import { LogoutComponent } from './logout/logout.component';
import { DiarioErrosComponent } from './diario-erros/diario-erros.component';
import { PessoaJuridicaListComponent } from './pessoa-juridica/pessoa-juridica-list/pessoa-juridica-list.component';
import { SecuredLayoutComponent } from './layouts/secured-layout/secured-layout.component';

export const routes: Routes = [

  {
    path: '',
    component: SecuredLayoutComponent,
    children: [
      { path: '', component: InicioComponent },
      { path: 'login-success', component: LoginSuccessComponent },
      { path: 'logout', component: LogoutComponent },
      { path: 'diario-erros', component: DiarioErrosComponent },
      { path: 'pessoa-juridica', loadChildren: 'app/pessoa-juridica/pessoa-juridica.module#PessoaJuridicaModule' }
	  { path: 'comunica��o',loadChildren: 'app/comunicacao/comunicacao.module#ComunicacaoModule'}
    ]
  },
  {
    path: '',
    component: LoginLayoutComponent,
    children: [
      {
        path: 'login',
        component: LoginComponent
      }
    ]
  },
  { path: '**', redirectTo: '' }
  /* jhipster-needle-add-lazy-module - JHipster will add lazy modules here */
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes);
