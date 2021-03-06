import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PageNotificationService } from '@basis/angular-components';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { SelectItem } from 'primeng/primeng';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import { EnumService } from '../../shared/enum.service';
import { ValidateCnpj } from '../../shared/validators/cnpj.validator';
import { CustomUtils } from '../../util/custom-utils';
import { MensagensUtils } from '../../util/mensagens-util';
import { PessoaJuridicaCadastro } from '../pessoa-juridica-cadastro.model';
import { PessoaJuridicaService } from '../pessoa-juridica.service';

@Component({
  selector: 'app-pessoa-juridica-form',
  templateUrl: './pessoa-juridica-form.component.html',
  styleUrls: ['./pessoa-juridica-form.component.css']
})
export class PessoaJuridicaFormComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;

  tituloPagina = MensagensUtils.CADASTRAR_PJ;
  pessoaJuridica: PessoaJuridicaCadastro;

  form: FormGroup;
  submitedForm = false;
  tiposEndereco: SelectItem[];
  ufs: SelectItem[];

  msgPadraoCampoObrigatorio = MensagensUtils.CAMPO_OBRIGATORIO;

  private routeSub: Subscription;

  constructor(private formBuilder: FormBuilder,
    private enumService: EnumService,
    private pageNotificationService: PageNotificationService,
    private pessoaJuridicaService: PessoaJuridicaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {

    this.pessoaJuridica = new PessoaJuridicaCadastro();
    this.buildReactiveForm();
    this.getTiposEndereco();

    this.routeSub = this.route.params.subscribe(params => {
      if (params['id']) {
        this.blockUI.start(MensagensUtils.CARREGANDO);
        this.tituloPagina = MensagensUtils.EDITAR_PJ;
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
    this.enumService.listarEnum(EnumService.SERVICO_TIPO_ENDERECO).subscribe((result: any) => {
      this.tiposEndereco = CustomUtils.entityToDropDown(result, CustomUtils.CAMPO_LABEL_PADRAO, CustomUtils.CAMPO_VALOR_PADRAO);
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
      this.router.navigate(['pessoa-juridica']);
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
        this.pageNotificationService.addErrorMessage(res.title);
    });
  }
}
