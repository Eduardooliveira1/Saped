import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Observable';
import { PessoaJuridicaService } from './../pessoa-juridica.service';
import { MensagensUtils } from './../../util/mensagens-util';
import { CustomUtils } from './../../util/custom-utils';
import { SelectItem } from 'primeng/primeng';
import { EnumService } from './../../shared/enum.service';
import { PessoaJuridicaCadastro } from './../pessoa-juridica-cadastro.model';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ValidateCnpj } from '../../shared/validators/cnpj.validator';
import { PageNotificationService } from '@basis/angular-components';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-pessoa-juridica-form',
  templateUrl: './pessoa-juridica-form.component.html',
  styleUrls: ['./pessoa-juridica-form.component.css']
})
export class PessoaJuridicaFormComponent implements OnInit {

  tituloPagina = "Cadastrar Pessoa Jurídica"
  pessoaJuridica: PessoaJuridicaCadastro;

  form: FormGroup;
  submitedForm = false;
  tiposEndereco: SelectItem[];
  ufs: SelectItem[];

  customUtil: CustomUtils;
  mensagensUtil: MensagensUtils

  msgPadraoCampoObrigatorio = "Campo Obrigatório.";

  private routeSub: Subscription;

  constructor(private formBuilder: FormBuilder,
    private enumService: EnumService,
    private pageNotificationService: PageNotificationService,
    private pessoaJuridicaService: PessoaJuridicaService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {

    this.pessoaJuridica = new PessoaJuridicaCadastro();
    this.customUtil = new CustomUtils;
    this.mensagensUtil = new MensagensUtils;
    this.buildReactiveForm();
    this.getTiposEndereco();

    this.routeSub = this.route.params.subscribe(params => {
      if (params['id']) {
        this.tituloPagina = "Editar Pessoa Jurídica";
        this.pessoaJuridicaService.obter(params['id']).subscribe(result => this.pessoaJuridica = result);
      }
    });

  }

  buildReactiveForm() {
    this.form = this.formBuilder.group({
      cnpj: new FormControl('', [Validators.required, ValidateCnpj]),
      sigla: new FormControl('', [Validators.required, Validators.maxLength(20)]),
      razaoSocial: new FormControl('', [Validators.required, Validators.maxLength(100)]),
      nomeFantasia: new FormControl('', [Validators.required, Validators.maxLength(100)]),
      cep: new FormControl(''),
      municipio: new FormControl('', [Validators.maxLength(100)]),
      bairro: new FormControl('', [Validators.maxLength(100)]),
      complemento: new FormControl('', [Validators.maxLength(100)]),
      endereco: new FormControl('', [Validators.maxLength(255)]),
    }, { updateOn: 'blur' });
  }

  cnpjInvalido() {
    return this.form != null
      && this.form.get('cnpj').errors
      && this.form.get('cnpj').errors.validCnpj
      && (this.form.get('cnpj').dirty || this.submitedForm);
  }

  getMessagemErroCnpj() {
    if (this.form.get('cnpj').errors.required) {
      return this.msgPadraoCampoObrigatorio;
    } else {
      return 'CNPJ inválido.';
    }
  }

  getTiposEndereco() {
    this.enumService.listarEnum("tipos-endereco").subscribe(result => {
      this.tiposEndereco = this.customUtil.entityToDropDown(result, 'descricao', 'id');
    })
  }

  salvarPessoaJuridica() {

    this.submitedForm = true;

    if (this.form.valid) {
      if (this.pessoaJuridica.id) {
        this.subscribeToSaveResponse(this.pessoaJuridicaService.atualizar(this.pessoaJuridica));
      } else {
        this.subscribeToSaveResponse(this.pessoaJuridicaService.cadastrar(this.pessoaJuridica));
      }
    } else {
      this.pageNotificationService.addErrorMessage(this.mensagensUtil.PREENCHA_CAMPOS_OBRIGATORIOS);
      return;
    }
  }

  private subscribeToSaveResponse(result: Observable<PessoaJuridicaCadastro>) {
    result.subscribe((res: PessoaJuridicaCadastro) => {
      this.pessoaJuridica = res;
      this.pageNotificationService.addSuccessMessage(this.mensagensUtil.REGISTRO_SALVO);
    }, (res) => {
      this.pageNotificationService.addErrorMessage(res.json().title);
    });
  }


}
