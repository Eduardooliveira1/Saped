import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {PageNotificationModule, PageNotificationService} from '@basis/angular-components';
import {ButtonModule} from 'primeng/button';
import {InputMaskModule, PasswordModule} from 'primeng/primeng';
import {PessoaRepresentanteService} from '../pessoa-juridica/pessoa-representante.service';
import {CustomComponentsModule} from '../shared/custom-components/custom-components.module';
// import {url, UrlParamsService} from '../shared/url-params.service';
import {EsqueciASenhaComponent} from './esqueci-a-senha-component/esqueci-a-senha.component';
import {LoginComponent} from './login-component/login.component';
import {loginRoute} from './login.route';
import {RedefinirSenhaComponent} from './redefinir-senha-component/redefinir-senha.component';

@NgModule({
  imports: [RouterModule.forChild(loginRoute), ButtonModule, CommonModule, FormsModule, PageNotificationModule, ReactiveFormsModule,
    CustomComponentsModule, InputMaskModule, PasswordModule],
  declarations: [LoginComponent, EsqueciASenhaComponent, RedefinirSenhaComponent],
  providers: [PessoaRepresentanteService, PageNotificationService, /*UrlParamsService, UrlParamsService, /*{
    provide: url, useValue: BASE_URL + 'login/redefinir-senha' }*/]
})
export class LoginModule { }
