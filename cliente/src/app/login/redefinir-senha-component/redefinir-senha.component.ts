import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {PageNotificationService} from '@basis/angular-components';
import {Observable} from '../../../../node_modules/rxjs';
import {PessoaRepresentanteService} from '../../pessoa-juridica/pessoa-representante.service';
import {UrlParams} from '../../shared/url-params';
// import {UrlParamsService} from '../../shared/url-params.service';
import {MensagensUtils} from '../../util/mensagens-util';

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
              private route: ActivatedRoute
              /* private httpClient: HttpClient/*,
              private urlParamsService: UrlParamsService*/) {
  }

  novaSenha: string;
  confirmarSenha: string;
  form: FormGroup;
  submitedForm = false;
  msgPadraoCampoObrigatorio = MensagensUtils.CAMPO_OBRIGATORIO;
  private result: Observable<UrlParams>;
  cnpj: string;

  ngOnInit() {
    // this.result = this.urlParamsService.search('cnpj');
    // const data = {cnpj: 'cnpj'};
    // this.httpClient.get<any>('http://localhost:4200/#/login/redefinir-senha',
    //   {params: data}).subscribe(restult => {
    //     this.cnpj = restult;
    // });
    this.route.queryParams.subscribe(params => {
      this.cnpj = params['cnpj'];
    });
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
        }
      }
    }

}
