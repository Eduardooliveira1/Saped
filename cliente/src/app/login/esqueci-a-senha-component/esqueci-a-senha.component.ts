import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {PageNotificationService} from '@basis/angular-components';
import {PessoaRepresentanteEmailECnpj} from '../../pessoa-juridica/pessoa-representante-email-e-cnpj';
import {PessoaRepresentanteService} from '../../pessoa-juridica/pessoa-representante.service';
import {ValidateCnpj} from '../../shared/validators/cnpj.validator';
import {MensagensUtils} from '../../util/mensagens-util';

@Component({
  selector: 'app-esqueci-a-senha',
  templateUrl: './esqueci-a-senha.component.html',
  styleUrls: ['../../layouts/login-layout/login-layout.component.css', 'esqueci-a-senha.component.css']
})
export class EsqueciASenhaComponent implements OnInit {

  eMail: string;
  cnpj: string;
  form: FormGroup;
  submitedForm = false;
  msgPadraoCampoObrigatorio = MensagensUtils.CAMPO_OBRIGATORIO;

  constructor(private pessoaRepresentanteService: PessoaRepresentanteService,
              private pageNotificationService: PageNotificationService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.buildForm();
  }

  confirmarAlteracao() {
    this.submitedForm = true;
    if (this.cnpj && this.eMail) {
      const pessoaRepresentanteEmailECnpj = new PessoaRepresentanteEmailECnpj(this.eMail, this.cnpj);
      this.pessoaRepresentanteService.validaEmailECnpj(pessoaRepresentanteEmailECnpj).subscribe((result: boolean) => {
        if (result) {
          this.pageNotificationService.addSuccessMessage(MensagensUtils.CONFIRMACAO_ENVIO_ALTERAR_SENHA);
        } else {
          this.pageNotificationService.addErrorMessage(MensagensUtils.EMAIL_OU_CNPJ_INVALIDO);
        }
      });
    }
  }

  buildForm() {
    this.form = this.formBuilder.group({
      cnpj: new FormControl('', [Validators.required, ValidateCnpj]),
      email: new FormControl('', [Validators.required, Validators.email])
    }, {updateOn: 'blur'});
  }

  cnpjInvalido() {
    return this.form != null
      && this.form.get('cnpj').errors
      && this.form.get('cnpj').errors.validCnpj
      && (this.form.get('cnpj').dirty || this.submitedForm);
  }

  getMessagemErroCnpj() {
    if (this.form.get('cnpj').errors.required) {
      return MensagensUtils.CAMPO_OBRIGATORIO;
    } else {
      return MensagensUtils.CNPJ_INVALIDO;
    }
  }

}
