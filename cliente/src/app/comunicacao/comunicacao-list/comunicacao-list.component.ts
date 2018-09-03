import { sapedUtil } from './../../shared/metodos/sapedUtil';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PageNotificationService } from '@basis/angular-components';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { ConfirmationService, DataTable } from 'primeng/primeng';
import { Observable } from 'rxjs/Observable';
import { MensagensUtils } from '../../util/mensagens-util';
import { Page } from '../../util/page';
import { Pageable } from '../../util/pageable-request';
import { ComunicacaoService } from '../comunicacao.service';
import { ComunicadoCadastro } from '../comunicado-cadastro.model';
import { ComunicacaoLista } from './comunicacao-lista.model';

@Component({
  selector: 'app-comunicacao-list',
  templateUrl: './comunicacao-list.component.html',
  styleUrls: ['./comunicacao-list.component.css']
})
export class ComunicacaoListComponent implements OnInit {

  @ViewChild('dataTable') dataTable: DataTable;

  @BlockUI() blockUI: NgBlockUI;

  result: Page<ComunicacaoLista>;
  filtro: string;
  submitedForm = false;
  formComunicacao: FormGroup;
  comunicado: ComunicadoCadastro;

  constructor(
    private formBuilder: FormBuilder,
    private comunicacaoService: ComunicacaoService,
    private router: Router,
    private pageNotificationService: PageNotificationService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.comunicado = new ComunicadoCadastro();
    this.buildReactiveForm();
  }

  buildReactiveForm() {
    this.formComunicacao = this.formBuilder.group({
      assunto: new FormControl('', [ Validators.maxLength(100)]),
      conteudo: new FormControl('', [Validators.maxLength(2000)]),
    }, { updateOn: 'blur' });
  }

   filtrar() {
    if (this.filtro && this.filtro.length >= 3) {
      this.dataTable.first = 0;
      this.pesquisar();
    } else if (!this.filtro || this.filtro.length == 0 ) {
      this.filtro = null;
      this.dataTable.first = 0;
      this.pesquisar();
    }
  }

  pesquisar() {
    const pageable = new Pageable(this.dataTable);

    this.blockUI.start(MensagensUtils.CARREGANDO);
    this.comunicacaoService.listarRepresentantes(this.filtro, pageable)
      .subscribe((result: any) => {
        this.blockUI.stop();
        this.result = result;
      }, error => {
        if(error.status === 401) {
          sapedUtil.navegarParaLogin(this.router);
        }
        this.blockUI.stop();
        this.pageNotificationService.addErrorMessage(MensagensUtils.ERRO_CARREGAR_DADOS);
      });
  }

  enviarComunicado() {
    this.submitedForm = true;
    if (this.formComunicacao.valid) {
      this.comunicado.representantes = selecao;
      this.subscribeToSaveResponse(this.comunicacaoService.enviar(this.comunicado));
      this.buildReactiveForm();}

  }

  private subscribeToSaveResponse(result: Observable<ComunicadoCadastro>) {
    this.blockUI.start(MensagensUtils.SALVANDO);
    result.subscribe((res: ComunicadoCadastro) => {
      this.blockUI.stop();

      this.pageNotificationService.addSuccessMessage(MensagensUtils.REGISTRO_SALVO); 
    }, (res) => {
      this.blockUI.stop();
      this.pageNotificationService.addErrorMessage(res.json().title);
    });
  }
}

