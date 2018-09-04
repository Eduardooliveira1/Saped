import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {PageNotificationService} from '@basis/angular-components';
import {LoginService} from '../../login-service';
import {UsuarioLogin} from '../../usuario-login-model';
import {MensagensUtils} from '../../util/mensagens-util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../../layouts/login-layout/login-layout.component.css']
})
export class LoginComponent implements OnInit {
  usuario: UsuarioLogin = new UsuarioLogin();

  constructor(private loginService: LoginService,
              private router: Router,
              private pageNotificationService: PageNotificationService) {
  }

  ngOnInit() {
  }

  fazerLogin() {
    if (this.usuario.username === '' || this.usuario.username == null) {
      this.pageNotificationService.addWarnMessage(MensagensUtils.USUARIO_INFORMAR_NOME);
      return false;
    }
    if (this.usuario.password == null || this.usuario.password === '') {
      this.pageNotificationService.addWarnMessage(MensagensUtils.USUARIO_INSERIR_SENHA);
      return false;
    }

    this.loginService.efetuarLogin(this.usuario).subscribe((authResult: any) => {
      localStorage.setItem('id_token', authResult.id_token);
      this.router.navigateByUrl('/inicio');
    }, error => {
      if (error.status === 401) {
        this.pageNotificationService.addErrorMessage(MensagensUtils.USUARIO_OU_SENHA_INVALIDOS);
      }
    });
  }
}
