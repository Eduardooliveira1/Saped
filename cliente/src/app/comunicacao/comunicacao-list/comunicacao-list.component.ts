import { PageNotificationService } from '@basis/angular-components';
import { MensagensUtils } from './../../util/mensagens-util';
import { NgBlockUI, BlockUI } from 'ng-block-ui';
import { Router } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Pageable } from '../../util/pageable-request';
import { ComunicacaoService } from '../comunicacao.service';
import { faUserFriends, faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { DataTable, ConfirmationService } from 'primeng/primeng';
import { Page } from '../../util/page';
import { ComunicacaoLista } from './comunicacao-lista.model';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ComunicadoCadastro } from './../comunicado-cadastro.model';
import { Observable } from 'rxjs/Observable';

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
  ultimoFiltro: string;
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
      this.ultimoFiltro = this.filtro;
      this.dataTable.first = 0;
      this.pesquisar();
    } else if (!this.filtro || this.filtro.length == 0 && this.ultimoFiltro) {
      this.filtro = null;
      this.ultimoFiltro = null;
      this.dataTable.first = 0;
      this.pesquisar();
    }
  }

  pesquisar() {
    let pageable = new Pageable(this.dataTable.first / this.dataTable.rows, this.dataTable.rows);
    pageable.setSort(this.dataTable.sortOrder, this.dataTable.sortField);

    this.blockUI.start(MensagensUtils.CARREGANDO);
    this.comunicacaoService.listarRepresentantes(this.filtro, pageable)
      .subscribe(result => {
        this.blockUI.stop();
        this.result = result.json();
      }, error => {
        this.blockUI.stop();
        this.pageNotificationService.addErrorMessage(MensagensUtils.ERRO_CARREGAR_DADOS);
      });
  }

  enviarComunicado(selecao) {

    this.submitedForm = true;
    if (this.formComunicacao.valid) {
      this.comunicado.representantes = selecao
      this.subscribeToSaveResponse(this.comunicacaoService.enviar(this.comunicado));
    }
    this.comunicado = new ComunicadoCadastro();
    
  
  }


  private subscribeToSaveResponse(result: Observable<ComunicadoCadastro>) {
    this.blockUI.start(MensagensUtils.SALVANDO);
    result.subscribe((res: ComunicadoCadastro) => {
      this.blockUI.stop();
      this.comunicado = res;
      this.pageNotificationService.addSuccessMessage(MensagensUtils.REGISTRO_SALVO); 
    }, (res) => {
      this.blockUI.stop();
      this.pageNotificationService.addErrorMessage(res.json().title);
    });
  }



}

