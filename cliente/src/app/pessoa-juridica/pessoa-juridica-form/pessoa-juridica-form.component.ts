import { CustomUtils } from './../../util/custom-utils';
import { SelectItem } from 'primeng/primeng';
import { EnumService } from './../../shared/enum.service';
import { PessoaJuridicaCadastro } from './../pessoa-juridica-cadastro.model';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ValidateCnpj } from '../../shared/validators/cnpj.validator';
import { PageNotificationService } from '@basis/angular-components';

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

  msgPadraoCampoObrigatorio = "Campo Obrigatório.";

  constructor(private formBuilder: FormBuilder,
    private enumService: EnumService,
    private pageNotificationService: PageNotificationService) { }

  ngOnInit() {

    this.pessoaJuridica = new PessoaJuridicaCadastro();
    this.customUtil = new CustomUtils;
    this.buildReactiveForm();
    this.getTiposEndereco();
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
      this.tiposEndereco =  this.customUtil.entityToDropDown(result,'descricao','id');
    })
  }

  salvarPessoaJuridica(){
    
    this.submitedForm = true;

    if(this.form.valid){

    }else{
      this.pageNotificationService.addErrorMessage('Preencha os campos obrigatórios.');
      return;
    }
  }


}
