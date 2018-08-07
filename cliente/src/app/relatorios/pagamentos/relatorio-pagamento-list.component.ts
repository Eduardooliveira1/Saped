import { MensagensUtils } from '../../util/mensagens-util';
import {DataTable} from 'primeng/primeng';
import {Component, OnInit, ViewChild} from '@angular/core';
import { PageNotificationService } from '@basis/angular-components';
import { Router } from '@angular/router';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import {Page} from '../../util/page';
import {Pageable} from '../../util/pageable-request';
import {RelatorioPagamentoList} from './relatorio-pagamento-list.model';
import {RelatoriosService} from '../relatorios.service';

@Component({
    selector: 'app-relatorio-pagamento-list',
    templateUrl: './relatorio-pagamento-list.component.html'})
export class RelatorioPagamentoListComponent implements OnInit {

    @BlockUI() blockUI: NgBlockUI;
    @ViewChild('dataTable') dataTable: DataTable;

    result: Page<RelatorioPagamentoList>;

    constructor(private relatoriosService: RelatoriosService,
                private router: Router,
                private pageNotificationService: PageNotificationService) {
    }

    ngOnInit() {
    }

    listarPagamentos() {
        const pageable = new Pageable(this.dataTable.first / this.dataTable.rows, this.dataTable.rows);
        pageable.setSort(this.dataTable.sortOrder, this.dataTable.sortField);

        this.blockUI.start(MensagensUtils.CARREGANDO);
        this.relatoriosService.listarPagamentos(pageable)
            .subscribe(result => {
                this.blockUI.stop();
                this.result = result.json();
            }, error => {
                this.blockUI.stop();
                this.pageNotificationService.addErrorMessage(error.toString() + MensagensUtils.ERRO_CARREGAR_DADOS);
            });
    }

}
