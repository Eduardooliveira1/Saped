import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {PageNotificationService} from '@basis/angular-components';
import {faPlusCircle, faTrashAlt} from '@fortawesome/free-solid-svg-icons';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {ConfirmationService} from 'primeng/primeng';
import {MensagensUtils} from '../../util/mensagens-util';
import {PessoaJuridicaService} from '../pessoa-juridica.service';
import {PessoaRepresentante} from '../pessoa-representante-model';
import {Telefone} from '../pessoa-representante-telefone';

declare var jQuery: any;

@Component({
  selector: 'app-pessoa-representante-list-component',
  templateUrl: './pessoa-representante-list-component.component.html',
  styleUrls: ['./pessoa-representante-list-component.component.css']
})
export class PessoaRepresentanteListComponentComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;

  formDinamico: FormGroup;

  @Input() listaRepresentantes: PessoaRepresentante[];
  pessoaRepresentate: PessoaRepresentante;
  telefone: Telefone[] = [];
  notificacaoAtivada = MensagensUtils.ENUM_NOTIFICACAO_SIM;
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
    this.buildFormDinamico();
  }

  buildReactiveForm() {
    this.form = this.formBuilder.group({
      nome: new FormControl('', [Validators.required]),
      cargo: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      notificacoes: new FormControl(''),
    }, { updateOn: 'blur' });
  }

  buildFormDinamico() {
    this.formDinamico = this.formBuilder.group({
      itemRows: this.formBuilder.array([this.buildFormControlTelefone()])
    });
  }

  buildFormControlTelefone() {
    return this.formBuilder.group({
      ddd: new FormControl('',[Validators.required]),
      telefone: new FormControl('', [Validators.required]),
    });
  }

  adicionarTelefone(index: number) {
    const control = <FormArray>this.formDinamico.controls['itemRows'];
    if (this.telefoneIsValid()) {
      this.telefone.push(new Telefone);
      control.push(this.buildFormControlTelefone());
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

    if(this.form.valid && this.telefoneIsValid()) {
      this.confirmationService.confirm({
        message: MensagensUtils.REPRESENTANTE_CONFIRMACAO_INCLUSAO,
        acceptLabel: MensagensUtils.SIM,
        rejectLabel: MensagensUtils.NAO,

        accept: () => {
          const control = <FormArray>this.formDinamico.controls['itemRows'];
          if(!this.telefone[control.length-1].ddd || !this.telefone[control.length-1].telefone) {
            this.removeUltimoItemDaListaTelefone();
          }
          this.pessoaRepresentate.telefone = this.telefone;
          this.listaRepresentantes.push(this.pessoaRepresentate);
          this.pessoaRepresentate = new PessoaRepresentante();
          this.telefone =  [];
          this.telefone.push(new Telefone);
          this.mostrarModalInserirRepresetante = false;
          this.buildFormDinamico();
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

  removerRepresentante(index) {
    this.confirmationService.confirm({
      message: MensagensUtils.REPRESENTANTE_CONFIRMACAO_EXCLUSAO,
      acceptLabel: MensagensUtils.SIM,
      rejectLabel: MensagensUtils.NAO,

      accept: () => {
        this.listaRepresentantes.splice(index, 1); 
      }
    });
  }

  cancelar() {
    this.telefone = [];
    this.telefone.push(new Telefone);
    this.mostrarModalInserirRepresetante=false;
    this.buildFormDinamico();

  }

  removeUltimoItemDaListaTelefone() {
    this.telefone.pop();
  }

  onChangeNotificacao($event){
    this.pessoaRepresentate.notificacao = $event ? MensagensUtils.ENUM_NOTIFICACAO_SIM : MensagensUtils.ENUM_NOTIFICACAO_NAO;
  }
}
