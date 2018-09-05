import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {PageNotificationService} from '@basis/angular-components';
import {MensagensUtils} from '../../../util/mensagens-util';

@Component({
  selector: 'app-cnpj-invalido',
  templateUrl: './cnpj-invalido-component.html',
  styleUrls: ['../../../layouts/login-layout/login-layout.component.css']
})
export class CnpjInvalidoComponent implements OnInit {

  constructor(private router: Router,
              private pageNotificationService: PageNotificationService) {
  }

  ngOnInit() {
    this.pageNotificationService.addErrorMessage(MensagensUtils.REDEFINIR_SENHA_LINK_INVALIDO);
    setTimeout(() => this.router.navigateByUrl('/login'), 2000);
  }

}
