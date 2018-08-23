import { PessoaRepresentanteService } from '../pessoa-representante.service';
import { PageNotificationService } from '@basis/angular-components';
import { MensagensUtils } from '../../util/mensagens-util';
import { NgBlockUI, BlockUI } from 'ng-block-ui';
import { Router } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Pageable } from '../../util/pageable-request';
import { PessoaJuridicaService } from '../pessoa-juridica.service';
import { faUserFriends, faEdit, faTrashAlt, faWindowClose } from '@fortawesome/free-solid-svg-icons';
import { DataTable, ConfirmationService } from 'primeng/primeng';
import { Page } from '../../util/page';
import { PessoaJuridicaLista } from './pessoa-juridica-lista.model';

@Component({
  selector: 'app-pessoa-juridica-list',
  templateUrl: './pessoa-juridica-list.component.html',
  styleUrls: ['./pessoa-juridica-list.component.css']
})
export class PessoaJuridicaListComponent implements OnInit {

  @ViewChild('dataTable') dataTable: DataTable;

  @BlockUI() blockUI: NgBlockUI;

  result: Page<PessoaJuridicaLista>;
  filtro: string;
  ultimoFiltro: string;

  faUserFriends = faUserFriends;
  faEdit = faEdit;
  faTrashAlt = faTrashAlt;
  faWindowClose = faWindowClose;

  rowexpansion: Boolean = true;

  constructor(private pessoaJuridicaService: PessoaJuridicaService,
              private pessoaRepresentanteService: PessoaRepresentanteService,
    private router: Router,
    private pageNotificationService: PageNotificationService,
    private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.dataTable.expandedRows = [];
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
    this.pessoaJuridicaService.listarDirigentes(this.filtro, pageable)
      .subscribe(result => {
        this.blockUI.stop();
        this.result = result.json();
      }, error => {
        this.blockUI.stop();
        this.pageNotificationService.addErrorMessage(MensagensUtils.ERRO_CARREGAR_DADOS);
      });
  }

  novaEmpresa() {
    this.router.navigateByUrl('pessoa-juridica/cadastro');
  }

  editarEmpresa(id) {
    this.router.navigateByUrl('pessoa-juridica/editar/' + id);
  }

  removerEmpresa(id) {
    this.confirmationService.confirm({
      message: MensagensUtils.CONFIRMACAO_EXCLUSAO,
      acceptLabel: MensagensUtils.SIM,
      rejectLabel: MensagensUtils.NAO,
      accept: () => {
        this.blockUI.start(MensagensUtils.CARREGANDO);
        this.pessoaJuridicaService.remover(id).subscribe(() => {
          this.blockUI.stop();
          this.pesquisar();
          this.pageNotificationService.addDeleteMsg();
        }, error=>{
          this.blockUI.stop();
          this.pageNotificationService.addErrorMessage(MensagensUtils.ERRO_EXCLUIR_REGISTRO);
        })
      }
    });
  }

  obtemRepresentantes(id: number, rowData) {
    this.blockUI.start(MensagensUtils.CARREGANDO);
    this.pessoaRepresentanteService.obterRepresentantes(id).subscribe(result => {
      this.blockUI.stop();
      rowData.representantes = result;
    }, error=>{
      this.blockUI.stop();
      this.pageNotificationService.addErrorMessage(MensagensUtils.ERRO_CARREGAR_DADOS);
    });
  }

  mostrarRepresentantes(rowData) {
    if (!rowData.representantes) {
      rowData.representantes = [];
      this.obtemRepresentantes(rowData.id, rowData);
    }
    
    let expandedRow = this.dataTable.expandedRows.find(r => r.id == rowData.id);

    if (expandedRow == null) {
      this.dataTable.expandedRows.push(rowData);
    }
  }

  fecharListaRepresentantes(rowData) {
    this.dataTable.expandedRows = this.dataTable.expandedRows.filter(row => row.id != rowData.id);
  }
}
