import {Component, OnInit, ViewChild} from '@angular/core';
import {PageNotificationService} from '@basis/angular-components';
import {BlockUI, NgBlockUI} from 'ng-block-ui';
import {DataTable} from 'primeng/primeng';
import {MensagensUtils} from '../../../util/mensagens-util';
import {Page} from '../../../util/page';
import {Pageable} from '../../../util/pageable-request';
import {RelatoriosService} from '../../relatorios.service';
import {FiltroRelatorioPagamentos} from '../filtro-relatorio-pagamento';
import {RelatorioPagamentoList} from '../relatorio-pagamento-list';

@Component({
  selector: 'app-relatorio-pagamento-list-component',
  templateUrl: './relatorio-pagamento-list.component.html'
})
export class RelatorioPagamentoListComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  @ViewChild('dataTable') dataTable: DataTable;
  result: Page<RelatorioPagamentoList>;
  private filtro: FiltroRelatorioPagamentos;

  constructor(private pageNotificationService: PageNotificationService,
              private relatoriosService: RelatoriosService) {
  }

  ngOnInit(): void {
  }

  listarPagamentos(filtro: FiltroRelatorioPagamentos) {
    this.filtro = filtro;
    this.setDataTable();
    const pageable = new Pageable(this.dataTable.first / this.dataTable.rows, this.dataTable.rows);
    pageable.setSort(this.dataTable.sortOrder, this.dataTable.sortField);
    this.blockUI.start(MensagensUtils.CARREGANDO);
    let response = null;
    if (filtro == null) {
      response = this.relatoriosService.listarPagamentosLazyLoad(pageable);
    } else {
      response = this.relatoriosService.listarPagamentos(filtro, pageable);
    }
    response.subscribe(result => {
      this.blockUI.stop();
      this.result = result;
    }, error => {
      this.blockUI.stop();
      this.pageNotificationService.addErrorMessage(error.toString() + MensagensUtils.ERRO_CARREGAR_DADOS);
    });
    this.dataTable.sortField = 'valorBoleto';
  }

  setDataTable() {
    this.dataTable.first = 0;
    this.dataTable.rows = 10;
  }

}
