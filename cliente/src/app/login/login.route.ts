import {Routes} from '@angular/router';
import {EsqueciASenhaComponent} from './esqueci-a-senha-component/esqueci-a-senha.component';
import {LoginComponent} from './login-component/login.component';

export const loginRoute: Routes = [
  { path: '', component: LoginComponent },
  { path: 'esqueci-a-senha', component: EsqueciASenhaComponent }
];

