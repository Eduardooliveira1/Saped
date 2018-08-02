import { PessoaRepresentante } from './../pessoa-representante-model';
import { Telefone } from './../pessoa-representante-telefone';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators, FormArray } from '@angular/forms';
import { PageNotificationService } from '@basis/angular-components';
import { MensagensUtils } from '../../util/mensagens-util';
import { faTrashAlt,faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { ConfirmationService } from 'primeng/primeng';
import { NgBlockUI, BlockUI } from 'ng-block-ui';
import { PessoaJuridicaService } from '../pessoa-juridica.service';
declare var jQuery: any;

@Component({
  selector: 'app-pessoa-representante-list-component',
  templateUrl: './pessoa-representante-list-component.component.html',
  styleUrls: ['./pessoa-representante-list-component.component.css']
})
export class PessoaRepresentanteListComponentComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;

  formDinamico: FormGroup;
  items: any[] = [];

  listaRepresentantes: PessoaRepresentante[] = [];
  pessoaRepresentate: PessoaRepresentante;
  telefone: Telefone[] = [];

  mostrarModalInserirRepresetante = false;
  form: FormGroup;
  submitedForm = false;
  msgPadraoCampoObrigatorio = MensagensUtils.CAMPO_OBRIGATORIO;
  msgEmailValido = MensagensUtils.EMAIL_VALIDO;
  faTrashAlt = faTrashAlt;
  faPlusCircle = faPlusCircle;

  constructor(private pessoaJuridicaService: PessoaJuridicaService,
              private formBuilder: FormBuilder,
              private pageNotificationService: PageNotificationService,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.pessoaRepresentate = new PessoaRepresentante;
    this.telefone =  [];
    this.telefone.push(new Telefone);
    this.buildReactiveForm();
    this.builFormDinamico();
  }

  buildReactiveForm() {
    this.form = this.formBuilder.group({
      nome: new FormControl('', [Validators.required]),
      cargo: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      notificacoes: new FormControl(''),
    }, { updateOn: 'blur' });
  }

  builFormDinamico() {
    this.formDinamico = this.formBuilder.group({
      itemRows: this.formBuilder.array([this.buildFormDinamico()])
    });
  }

  buildFormDinamico() {
    return this.formBuilder.group({
      ddd: new FormControl('',[Validators.required]),
      telefone: new FormControl('', [Validators.required]),
    });
  }

  adicionarTelefone(index: number) {
    const control = <FormArray>this.formDinamico.controls['itemRows'];
    if (this.telefoneIsValid()) {
      this.telefone.push(new Telefone);
      control.push(this.buildFormDinamico());
    } else {
      this.pageNotificationService.addWarnMessage(MensagensUtils.REPRESENTANTE_INSERIR_TELEFONE);
    }
  }

  telefoneIsValid() {
    const control = <FormArray>this.formDinamico.controls['itemRows'];
    var ultimoTelefone = this.telefone[control.length-1];

    return  ultimoTelefone != null && 
            ultimoTelefone.telefone != null && 
            ultimoTelefone.telefone.replace(/[^\d]/g, '').length == 9 && 
            ultimoTelefone.ddd != null && 
            ultimoTelefone.ddd.replace(/[^\d]/g, '').length == 2;
  }
  
  abrirModalRepresentante() {
    this.submitedForm = false;
    this.pessoaRepresentate = new PessoaRepresentante();
    this.form.reset()
    this.formDinamico.reset();
    this.mostrarModalInserirRepresetante = true;

  }

  incluirRepresentante() {
    this.submitedForm = true;

    if (this.form.valid && this.telefone[0].ddd && this.telefone[0].telefone) {
      this.confirmationService.confirm({
        message: MensagensUtils.REPRESENTANTE_CONFIRMACAO_INCLUSAO,
        acceptLabel: MensagensUtils.SIM,
        rejectLabel: MensagensUtils.NAO,

        accept: () => {
          const control = <FormArray>this.formDinamico.controls['itemRows'];
          if(!this.telefone[control.length-1].ddd || !this.telefone[control.length-1].telefone) {
            this.removeUltimoItemDaListaTelefone(control.length-1);
          }

          this.unificaTelefone(this.telefone);
          this.pessoaRepresentate.telefone = this.telefone;
          this.listaRepresentantes.push(this.pessoaRepresentate);
          this.pessoaRepresentate = new PessoaRepresentante();
          this.telefone =  [];
          this.telefone.push(new Telefone);
          this.mostrarModalInserirRepresetante = false;
          this.builFormDinamico();
        },
        reject: () => {
          this.mostrarModalInserirRepresetante = false;
        }
      });  
    } else {
      this.pageNotificationService.addErrorMessage(MensagensUtils.PREENCHA_CAMPOS_OBRIGATORIOS);
      return;
    }
  }

  setRepresentantes(representantes: PessoaRepresentante[]) {
    
  this.converteEnumparaBool(representantes);
    this.listaRepresentantes = representantes;
  }

  removerRepresentante(index) {

    this.confirmationService.confirm({
      message: MensagensUtils.REPRESENTANTE_CONFIRMACAO_EXCLUSAO,
      acceptLabel: MensagensUtils.SIM,
      rejectLabel: MensagensUtils.NAO,

      accept: () => {
        this.listaRepresentantes = jQuery.grep( this.listaRepresentantes, (n, i) => {
          return i !== index;
        }); 
      }
    });
  }

  cancelar() {
    this.telefone = [];
    this.telefone.push(new Telefone);
    this.mostrarModalInserirRepresetante=false;
    this.builFormDinamico();
  }

  unificaTelefone( telefones: Telefone[] ) {
    for(let tel of telefones)
    {
      tel.telefoneDdd = tel.ddd + tel.telefone;
    }
  }

  removeUltimoItemDaListaTelefone(index: number) {
    this.telefone.pop();
  }

  converteEnumparaBool(representantes: PessoaRepresentante[]) {
    for(let representante of  representantes) {
      if(representante.notificacao == 'S') {
        representante.notificacaoFront = true;
      }
      else {
        representante.notificacaoFront = false;
      }
    }
  }

  getRepresentates() {
    return this.listaRepresentantes;
  }

}
