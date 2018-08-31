import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {PageNotificationModule, PageNotificationService} from '@basis/angular-components';
import {ButtonModule} from 'primeng/button';
import {InputMaskModule} from 'primeng/primeng';
import {PessoaRepresentanteService} from '../pessoa-juridica/pessoa-representante.service';
import {CustomComponentsModule} from '../shared/custom-components/custom-components.module';
import {EsqueciASenhaComponent} from './esqueci-a-senha-component/esqueci-a-senha.component';
import {LoginComponent} from './login-component/login.component';
import {loginRoute} from './login.route';

@NgModule({
  imports: [RouterModule.forChild(loginRoute), ButtonModule, CommonModule, FormsModule, PageNotificationModule, ReactiveFormsModule,
    CustomComponentsModule, InputMaskModule],
  declarations: [LoginComponent, EsqueciASenhaComponent],
  providers: [PessoaRepresentanteService, PageNotificationService]
})
export class LoginModule { }
