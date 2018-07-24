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
import { BlockUI, NgBlockUI } from 'ng-block-ui';

@Component({
  selector: 'app-pessoa-juridica-form',
  templateUrl: './pessoa-juridica-form.component.html',
  styleUrls: ['./pessoa-juridica-form.component.css']
})
export class PessoaJuridicaFormComponent implements OnInit {


  @BlockUI() blockUI: NgBlockUI;

  tituloPagina = "Cadastrar Pessoa Jurídica"
  pessoaJuridica: PessoaJuridicaCadastro;

  form: FormGroup;
  submitedForm = false;
  tiposEndereco: SelectItem[];
  ufs: SelectItem[];

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
    this.buildReactiveForm();
    this.getTiposEndereco();

    this.routeSub = this.route.params.subscribe(params => {
      if (params['id']) {
        this.blockUI.start(MensagensUtils.CARREGANDO);
        this.tituloPagina = "Editar Pessoa Jurídica";
        this.pessoaJuridicaService.obter(params['id']).subscribe(result => {
          this.blockUI.stop();
          this.pessoaJuridica = result
        }, error=>{
          this.blockUI.stop();
          this.pageNotificationService.addErrorMessage(MensagensUtils.ERRO_CARREGAR_DADOS);
        });
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
      return MensagensUtils.CAMPO_OBRIGATORIO;
    } else {
      return MensagensUtils.CNPJ_INVALIDO;
    }
  }

  getTiposEndereco() {
    this.enumService.listarEnum("tipos-endereco").subscribe(result => {
      this.tiposEndereco = CustomUtils.entityToDropDown(result, 'descricao', 'id');
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
      this.pageNotificationService.addErrorMessage(MensagensUtils.PREENCHA_CAMPOS_OBRIGATORIOS);
      return;
    }
  }

  private subscribeToSaveResponse(result: Observable<PessoaJuridicaCadastro>) {
    this.blockUI.start(MensagensUtils.SALVANDO);
    result.subscribe((res: PessoaJuridicaCadastro) => {
      this.blockUI.stop();
      this.pessoaJuridica = res;
      this.pageNotificationService.addSuccessMessage(MensagensUtils.REGISTRO_SALVO);
    }, (res) => {
      this.blockUI.stop();
      this.pageNotificationService.addErrorMessage(res.json().title);
    });
  }

}
