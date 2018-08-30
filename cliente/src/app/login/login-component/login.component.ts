import {Component, OnInit} from '@angular/core';
import {AuthService} from '@basis/angular-components';

import {Usuario} from '../../usuario/usuario';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../../layouts/login-layout/login-layout.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private auth: AuthService<Usuario>) {
  }

  ngOnInit() {
    // this.auth.redirectLogin();
  }

}
