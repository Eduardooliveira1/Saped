import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {PageNotificationService} from '@basis/angular-components';
import {PessoaJuridicaService} from '../../pessoa-juridica/pessoa-juridica.service';
import {PessoaRepresentanteService} from '../../pessoa-juridica/pessoa-representante.service';
import {CnpjConstants} from '../../shared/constants/cnpj-constants';
import {MensagensUtils} from '../../util/mensagens-util';
import {CredenciaisNovaSenha} from './credenciais-nova-senha';

@Component({
  selector: 'app-redefinir-a-senha',
  templateUrl: './redefinir-senha.component.html',
  styleUrls: ['../../layouts/login-layout/login-layout.component.css',
    '../esqueci-a-senha-component/esqueci-a-senha.component.css']
})
export class RedefinirSenhaComponent implements OnInit {

  constructor(private pessoaRepresentanteService: PessoaRepresentanteService,
              private pageNotificationService: PageNotificationService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private pessoaJuridicaService: PessoaJuridicaService) {
  }

  novaSenha: string;
  confirmarSenha: string;
  form: FormGroup;
  submitedForm = false;
  msgPadraoCampoObrigatorio = MensagensUtils.CAMPO_OBRIGATORIO;
  cnpj: string;
  credenciaisNovaSenha: CredenciaisNovaSenha;

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.cnpj = params['cnpj'];
    });
    if (!CnpjConstants.isValidNumericCnpj(this.cnpj)) {
      this.router.navigateByUrl('/login/cnpj-invalido');
    }
    this.buildForm();
  }

  campoInvalido(id: string) {
    return this.form != null
      && this.form.get(id).errors
      && (this.form.get(id).dirty || this.submitedForm);
  }

  buildForm() {
    this.form = this.formBuilder.group({
      novaSenha: new FormControl('', [Validators.required]),
      confirmarSenha: new FormControl('', [Validators.required])
    }, {updateOn: 'blur'});
  }

  confirmarAlteracao() {
    this.submitedForm = true;
    if (this.novaSenha && this.confirmarSenha) {
      if (this.confirmarSenha !== this.novaSenha) {
        this.pageNotificationService.addErrorMessage(MensagensUtils.CAMPOS_SENHA_DIFERENTES);
      } else if (this.novaSenha.length < 8) {
        this.pageNotificationService.addErrorMessage(MensagensUtils.SENHA_MENOR_QUE_8);
      } else {
        this.credenciaisNovaSenha = new CredenciaisNovaSenha(this.cnpj, this.novaSenha);
        this.pessoaJuridicaService.alterarSenha(this.credenciaisNovaSenha).subscribe(() =>
          this.pageNotificationService.addSuccessMessage(MensagensUtils.SENHA_ALTERADA),
          error => {
            this.pageNotificationService.addErrorMessage(error.message);
          });
      }
    }
  }

}
