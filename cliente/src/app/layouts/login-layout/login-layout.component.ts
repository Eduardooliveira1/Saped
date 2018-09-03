import { MensagensUtils } from '../../util/mensagens-util';
import { PageNotificationService } from '@basis/angular-components';
import { Component, OnInit } from '@angular/core';
import { UsuarioLogin } from '../../usuario-login-model';
import { LoginService } from '../../login-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-layout',
  templateUrl: './login.layout.component.html',
  styleUrls: ['./login-layout.component.css']
})
export class LoginLayoutComponent implements OnInit {

  usuario: UsuarioLogin = new UsuarioLogin();

  constructor( private loginService: LoginService,
               private router: Router,
               private pageNotificationService: PageNotificationService) { 
  }
  
  ngOnInit() {
  }

  fazerLogin() {
    if(this.usuario.username == "" || this.usuario.username == null) {
      this.pageNotificationService.addWarnMessage(MensagensUtils.USUARIO_INFORMAR_NOME);
      return false;
    }
    if (this.usuario.password == null || this.usuario.password == "") {
      this.pageNotificationService.addWarnMessage(MensagensUtils.USUARIO_INSERIR_SENHA);
      return false;
    }

    this.loginService.efetuarLogin(this.usuario).subscribe((authResult: any) => {
      localStorage.setItem('id_token', authResult.id_token);
      this.router.navigateByUrl('');
    }, error => {
      if(error.status === 401) {
        this.pageNotificationService.addErrorMessage(MensagensUtils.USUARIO_OU_SENHA_INVALIDOS);
      }
    });

  }

  
}
