import {ModuleWithProviders} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DiarioErrosComponent} from './diario-erros/diario-erros.component';
import {InicioComponent} from './inicio/inicio.component';
import {LoginLayoutComponent} from './layouts/login-layout/login-layout.component';
import {SecuredLayoutComponent} from './layouts/secured-layout/secured-layout.component';
import {LoginSuccessComponent} from './login-success/login-success.component';
import {EsqueciASenhaComponent} from './login/esqueci-a-senha-component/esqueci-a-senha.component';

import {LoginComponent} from './login/login-component/login.component';
import {LogoutComponent} from './logout/logout.component';

export const routes: Routes = [

  {
    path: '',
    component: SecuredLayoutComponent,
    children: [
      { path: '', component: InicioComponent },
      { path: 'login-success', component: LoginSuccessComponent },
      { path: 'logout', component: LogoutComponent },
      { path: 'diario-erros', component: DiarioErrosComponent },
      { path: 'pessoa-juridica', loadChildren: 'app/pessoa-juridica/pessoa-juridica.module#PessoaJuridicaModule' },
      { path: 'cadastrar-cobranca', loadChildren: 'app/cadastrar-cobranca/cadastrar-cobranca.module#CadastrarCobrancaModule' },
 { path: 'relatorios', loadChildren: 'app/relatorios/relatorios.module#RelatoriosModule' }
    ]
  },
  {
    path: '',
    component: LoginLayoutComponent,
    children: [
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'esqueci-a-senha',
        component: EsqueciASenhaComponent
      }
    ]
  },
  { path: '**', redirectTo: '' }
  /* jhipster-needle-add-lazy-module - JHipster will add lazy modules here */
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes);
